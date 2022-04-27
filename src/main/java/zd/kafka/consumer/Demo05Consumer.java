package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo01Message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: kafka广播消费
 * @author: Mr.Zhang
 * @create: 2022-04-27 10:06
 **/
@Component
public class Demo05Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "demo05-consumer-group-" + Demo01Message.TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}") // <X>
    public void onMessage(Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
