package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo07Message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 消费者事务类
 * @author: Mr.Zhang
 * @create: 2022-04-27 11:44
 **/
//@Component
public class Demo07Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1.修改 spring.kafka.producer.acks=all 配置，不然在启动时会报
     * 2.增加 transaction-id-prefix=demo. 配置，事务编号的前缀。需要保证相同应用配置相同，不同应用配置不同
     * 3.增加 spring.kafka.consumer.properties.isolation.level=read_committed 配置，Consumer
     * 仅读取已提交的消息。😈 一定要配置！！！被坑惨了，当时以为自己的事务消息怎么就是不生效，原来少加了这个。
     * @param message
     */
    @KafkaListener(topics = Demo07Message.TOPIC,
            groupId = "demo07-consumer-group-" + Demo07Message.TOPIC)
    public void onMessage(Demo07Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
