package zd.kafka;

import org.junit.Test;
import org.slf4j.Logger;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;
import zd.kafka.producer.Demo01Producer;
import zd.kafka.producer.Demo06Producer;
import zd.kafka.producer.Demo07Producer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 测试类
 * @author: Mr.Zhang
 * @create: 2022-04-25 16:43
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo01Producer producer;

    @Autowired
    private Demo06Producer  demo06Producer;

    @Autowired
    private Demo07Producer demo07Producer;

//    @Test
//    public void testSyncSend() throws ExecutionException, InterruptedException {
//        int id = (int)(System.currentTimeMillis()/1000);
//        SendResult sendResult = producer.syncSend(id);
//        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, sendResult);
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

//    @Test
//    public void testASyncSend() throws InterruptedException {
//        int id = (int) (System.currentTimeMillis() / 1000);
//        producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
//
//            @Override
//            public void onFailure(Throwable e) {
//                logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
//            }
//
//            @Override
//            public void onSuccess(SendResult<Object, Object> result) {
//                logger.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
//            }
//
//        });
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }


    /**
     * batch-size: 16384 # 每次批量发送消息的最大数量
     * buffer-memory: 33554432 # 每次批量发送消息的最大内存
     * properties:
     *   linger:
     *       ms: 30000
     *
     * kafka批量发送，一二参数满足条件发送，三参数到了等待时间立刻发送，不用考虑一二参数是否符合
     */
//    @Test
//    public void testASyncSendAll() throws InterruptedException {
//        logger.info("[testASyncSend][开始执行]");
//
//        for (int i = 0; i < 3; i++) {
//            int id = (int) (System.currentTimeMillis() / 1000);
//            producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
//
//                @Override
//                public void onFailure(Throwable e) {
//                    logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
//                }
//
//                @Override
//                public void onSuccess(SendResult<Object, Object> result) {
//                    logger.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
//                }
//
//            });
//
//            // 故意每条消息之间，隔离 10 秒
//            Thread.sleep(10 * 1000L);
//        }
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

    /**
     * 模拟消费失败场景
     * @throws ExecutionException
     * @throws InterruptedException
     */
//    @Test
//    public void testSyncSendError() throws ExecutionException, InterruptedException {
//        int id = (int) (System.currentTimeMillis() / 1000);
//        SendResult result = producer.syncSend(id);
//        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }


//    @Test
//    public void test() throws InterruptedException {
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

    /**
     * 广播模式，使同一个消费者组的所有消费者都有消费所有分区消息
     * @throws ExecutionException
     * @throws InterruptedException
     */
//    @Test
//    public void testSyncSendRadio() throws ExecutionException, InterruptedException {
//        int id = (int) (System.currentTimeMillis() / 1000);
//        SendResult result = producer.syncSend(id);
//        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

    /**
     * 测试kafka并发消费
     * @throws ExecutionException
     * @throws InterruptedException
     */
//    @Test
//    public void testSyncSend() throws ExecutionException, InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            int id = (int) (System.currentTimeMillis() / 1000);
//            SendResult result = demo06Producer.syncSend(id);
//            logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
//        }
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

//    @Test
//    public void testSyncSendOrderly() throws ExecutionException, InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            int id = 1;
//            SendResult result = demo06Producer.syncSendOrderly(id);
//            logger.info("[testSyncSend][发送编号：[{}] 发送队列：[{}]]", id, result.getRecordMetadata().partition());
//        }
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

    /**
     * 测试kafka事务
     */
//    @Test
//    public void testSyncSendInTransaction() throws ExecutionException, InterruptedException {
//        int id = (int) (System.currentTimeMillis() / 1000);
//        demo07Producer.syncSendInTransaction(id, new Runnable() {
//
//            @Override
//            public void run() {
//                logger.info("[run][我要开始睡觉了]");
//                try {
//                    Thread.sleep(10 * 1000L);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                logger.info("[run][我睡醒了]");
//            }
//
//        });
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }


    /**
     * 测试kafka偏移量提交
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        for (int id = 1; id <= 2; id++) {
            SendResult result = demo07Producer.syncSend(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
