package com.microblog.points.service.message.handler;

import com.microblog.common.module.points.PointsDto;
import com.microblog.points.service.PointsService;
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
public class PointsMessageHandler implements MessageHandler{

    private AbstractSerialize serialize = new JdkSerializeUtil();
    private PointsService pointsService;

    /***
     * 执行任务的线程池
     */
    private ExecutorService taskExecutor  = new ThreadPoolExecutor(100,
            100,
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public PointsMessageHandler(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @Override
    public ConsumeConcurrentlyStatus handler(List<MessageExt> msgs) {

        log.debug("Email:The msgs size is {}",msgs.size() );

        Future<ConsumeConcurrentlyStatus> future = null;
        for(MessageExt msg:msgs){
            byte[] body =  msg.getBody();
            PointsDto pointsDto = serialize.deserialize(body,null);
            future =  taskExecutor.submit(new PointsMessageHandler.PointsSendHandle(pointsDto));
        }
        try{

            ConsumeConcurrentlyStatus status = future.get();
            return status;
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }

    /**
     *功能描述
     * @author lgj
     * @Description  执行具体积分处理的线程
     * @date 5/12/19
     */
    private class PointsSendHandle implements Callable{

        private PointsDto pointsDto;

        public PointsSendHandle(PointsDto pointsDto) {
            this.pointsDto = pointsDto;

        }

        @Override
        public Object call() throws Exception {

            ConsumeConcurrentlyStatus status  = null;
            try{
                 pointsService.handlePoints(pointsDto.getUserId(),pointsDto.getType());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            catch(Exception ex){
                log.error(ex.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }

        }
    }
