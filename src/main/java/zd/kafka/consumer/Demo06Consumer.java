package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo06Message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 消费者
 * @author: Mr.Zhang
 * @create: 2022-04-27 10:42
 **/
@Component
public class Demo06Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * concurrency = "2"相当于创建了两个消费者并发消费一个主题
     * @param message
     */
    @KafkaListener(topics = Demo06Message.TOPIC,
            groupId = "demo06-consumer-group-" + Demo06Message.TOPIC,
            concurrency = "2")
    public void onMessage(Demo06Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
