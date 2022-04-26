package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo01Message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 消费者
 * @author: Mr.Zhang
 * @create: 2022-04-25 16:07
 **/
@Component
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo01Message.TOPIC,groupId = "demo01-consumer-group-"+Demo01Message.TOPIC)
    public void onMessage(Demo01Message message){
        logger.info("[onMessage][线程编号：{}消息内容：{}]",
                Thread.currentThread().getId(),message);
    }
}
