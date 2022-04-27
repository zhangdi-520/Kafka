package zd.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo06Message;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 生产者
 * @author: Mr.Zhang
 * @create: 2022-04-27 10:40
 **/
@Component
public class Demo06Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo01Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo06Message.TOPIC, message).get();
    }
}
