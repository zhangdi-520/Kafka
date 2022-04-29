package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo07Message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 提交消费进度
 * @author: Mr.Zhang
 * @create: 2022-04-28 10:32
 **/
@Component
public class Demo08Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * enable-auto-commit: false # 使用 Spring-Kafka 的消费进度的提交机制
     * ack-mode: MANUAL
     * 通过调用其 #acknowledge() 方法，可以提交当前消息的 Topic 的 Partition 的消费进度。
     * @param message
     * @param acknowledgment
     */
    @KafkaListener(topics = Demo07Message.TOPIC,
            groupId = "demo08-consumer-group-" + Demo07Message.TOPIC)
    public void onMessage(Demo07Message message, Acknowledgment acknowledgment) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // 提交消费进度,只提交为奇数的消费进度
        if (message.getId() % 2 == 1) {
            acknowledgment.acknowledge();
        }
    }
}
