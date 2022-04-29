package zd.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo07Message;

import java.util.concurrent.ExecutionException;

/**
 * @version V1.0
 * @program: Kafka
 * @description: kafka的事务
 * @author: Mr.Zhang
 * @create: 2022-04-27 11:13
 **/
@Component
public class Demo07Producer {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String syncSendInTransaction(Integer id, Runnable runner){
        return kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<Object, Object, String>() {

            @Override
            public String doInOperations(KafkaOperations<Object, Object> kafkaOperations) {
                // 创建 Demo07Message 消息
                Demo07Message message = new Demo07Message();
                message.setId(id);
                try {
                    SendResult<Object, Object> sendResult = kafkaOperations.send(Demo07Message.TOPIC, message).get();
                    logger.info("[doInOperations][发送编号：[{}] 发送结果：[{}]]", id, sendResult);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // 本地业务逻辑... biubiubiu
                runner.run();

                // 返回结果
                return "success";
            }

        });
    }

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo08Message 消息
        Demo07Message message = new Demo07Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo07Message.TOPIC, message).get();
    }
}
