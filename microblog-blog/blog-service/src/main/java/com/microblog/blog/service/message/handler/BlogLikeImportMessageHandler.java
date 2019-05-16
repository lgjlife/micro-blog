package com.microblog.blog.service.message.handler;

import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.JdkSerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.*;


/**
 *功能描述
 * @author lgj
 * @Description  积分消息处理
 * @date 5/12/19
*/
@Slf4j
public class BlogLikeImportMessageHandler implements MessageHandler{

    private AbstractSerialize serialize = new JdkSerializeUtil();


    /***
     * 执行任务的线程池
     */
    private ExecutorService taskExecutor  = new ThreadPoolExecutor(1,
            1,
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @Override
    public ConsumeConcurrentlyStatus handler(List<MessageExt> msgs) {

        log.debug("The msgs size is {}",msgs.size() );

        taskExecutor.submit(new BlogLikeImportSendHandle());
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;

    }

    /**
     *功能描述
     * @author lgj
     * @Description  执行具体积分处理的线程
     * @date 5/12/19
     */
     class BlogLikeImportSendHandle implements Callable{


        @Override
        public Object call() throws Exception {

            return null;
        }

    }


}
