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

    /**
     * 相同分区的消息是按照顺序发送的，这里使用key的hash策略将要顺序消费的消息发送到同一个分区，
     * 保证消息被消费的顺序性，并发消费的模式也支持顺序消费
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SendResult syncSendOrderly(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo01Message 消息
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 同步发送消息
        // 因为我们使用 String 的方式序列化 key ，所以需要将 id 转换成 String
        return kafkaTemplate.send(Demo06Message.TOPIC, String.valueOf(id), message).get();
    }
}
