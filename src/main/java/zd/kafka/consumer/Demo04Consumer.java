package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo01Message;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 消费重试
 * @author: Mr.Zhang
 * @create: 2022-04-26 11:08
 **/
//@Component
public class Demo04Consumer {

    private AtomicInteger count = new AtomicInteger(0);

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "demo04-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(Demo01Message message) {
        System.out.println("=====================================================================");
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("我就是故意抛出一个异常");
    }

    @KafkaListener(topics = Demo01Message.TOPIC+".DLT",
            groupId = "demo04-consumer-group-" + Demo01Message.TOPIC)
    public void onMessageRetry(Demo01Message message) {
        System.out.println("+++++++++++++++++++++++++++++++++++=");
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
    }
}
