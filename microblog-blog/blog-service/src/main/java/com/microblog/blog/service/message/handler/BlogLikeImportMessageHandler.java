package com.microblog.blog.service.message.handler;

import com.microblog.blog.service.handler.BlogLikeHandler;
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

    private BlogLikeHandler blogLikeHandler;

    public BlogLikeImportMessageHandler(BlogLikeHandler blogLikeHandler) {
        this.blogLikeHandler = blogLikeHandler;
    }

    /***
     * 执行任务的线程池，只需要一个任务线程即可
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

        //不需要进行幂等性处理
        taskExecutor.submit(new BlogLikeImportSendHandle(blogLikeHandler));
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;

    }

    /**
     *功能描述
     * @author lgj
     * @Description  执行具体积分处理的线程
     * @date 5/12/19
     */
     class BlogLikeImportSendHandle implements Runnable{
        private BlogLikeHandler blogLikeHandler;


        public BlogLikeImportSendHandle(BlogLikeHandler blogLikeHandler) {
            this.blogLikeHandler = blogLikeHandler;
        }

        @Override
        public void run() {
            blogLikeHandler.blogLikeImportHandler();
        }

    }


}
