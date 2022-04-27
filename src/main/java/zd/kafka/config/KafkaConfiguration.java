package zd.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.*;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * @version V1.0
 * @program: Kafka
 * @description: kafka配置类
 * @author: Mr.Zhang
 * @create: 2022-04-26 10:28
 **/
@Configuration
public class KafkaConfiguration {


    /**
     * 死信队列名为topic名+“。DLT”
     * @param template
     * @return
     */
    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?,?> template){
        //创建 DeadLetterPublishingRecoverer 对象，它负责实现，在重试到达最大次数时，Consumer 还是消费失败时，该消息就会发送到死信队列。
        DeadLetterPublishingRecoverer deadLetterPublishingRecoverer = new DeadLetterPublishingRecoverer(template);
        //创建 FixedBackOff 对象。这里，我们配置了重试 3 次，每次固定间隔 30 秒。当然，胖友可以选择 BackOff 的另一个子类 ExponentialBackOff 实现，提供指数递增的间隔时间。
        FixedBackOff fixedBackOff = new FixedBackOff(3 * 1000L, 2L);
        //创建 SeekToCurrentErrorHandler 对象，负责处理异常，串联整个消费重试的整个过程
        return new SeekToCurrentErrorHandler(deadLetterPublishingRecoverer,fixedBackOff);
    }

    /**
     *批量消费失败的重试，批量消息的重试暂时不支持死信队列，所以没有DeadLetterPublishingRecoverer
     */
//    @Bean
//    @Primary
//    public BatchErrorHandler kafkaBatchErrorHandler() {
//        // 创建 SeekToCurrentBatchErrorHandler 对象
//        SeekToCurrentBatchErrorHandler batchErrorHandler = new SeekToCurrentBatchErrorHandler();
//        // 创建 FixedBackOff 对象
//        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
//        batchErrorHandler.setBackOff(backOff);
//        // 返回
//        return batchErrorHandler;
//    }
}
