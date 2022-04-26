package zd.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zd.kafka.message.Demo01Message;

import java.util.List;

/**
 * @version V1.0
 * @program: Kafka
 * @description: kafka批量消费
 * @author: Mr.Zhang
 * @create: 2022-04-26 10:12
 **/
@Component
public class Demo02Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * spring.kafka.listener.type
     * spring.kafka.consumer.max-poll-records
     * spring.kafka.consumer.fetch-min-size
     * spring.kafka.consumer.fetch-max-wait
     * 配置类新增四个配置项控制kafka批量消费，onMessage方法参数数据结构改为list
     */
    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "demo02-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(List<Demo01Message> messages) {
        logger.info("[onMessage][线程编号:{} 消息数量：{}]", Thread.currentThread().getId(), messages.size());
    }
}
