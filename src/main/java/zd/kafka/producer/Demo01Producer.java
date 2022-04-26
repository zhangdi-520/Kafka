package zd.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import zd.kafka.message.Demo01Message;

import java.util.concurrent.ExecutionException;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 生产者
 * @author: Mr.Zhang
 * @create: 2022-04-25 15:44
 **/
@Component
public class Demo01Producer {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setId(id);
        return kafkaTemplate.send(Demo01Message.TOPIC,demo01Message).get();
    }

    public ListenableFuture<SendResult<Object,Object>> asyncSend(Integer id){
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setId(id);
        return kafkaTemplate.send(Demo01Message.TOPIC,demo01Message);
    }
}
