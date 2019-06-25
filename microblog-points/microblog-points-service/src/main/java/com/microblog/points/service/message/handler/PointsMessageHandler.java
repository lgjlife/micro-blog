package com.microblog.points.service.message.handler;

import com.microblog.common.module.points.PointsDto;
import com.microblog.points.service.PointsService;
import com.microblog.points.service.utils.IdempotencyHandler;
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

    private IdempotencyHandler idempotencyHandler;

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

        PointsDto pointsDto = null;
        for(MessageExt msg:msgs){
            byte[] body =  msg.getBody();
            pointsDto = serialize.deserialize(body,null);

            if(idempotencyHandler.checkConsumption(pointsDto.getMessageId(),1,TimeUnit.HOURS)){
                //消息未被处理
                future =  taskExecutor.submit(new PointsMessageHandler.PointsSendHandle(pointsDto));
            }
            else {
                //消息已经表被处理
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        }
        try{
            ConsumeConcurrentlyStatus status = future.get();
            if(status == ConsumeConcurrentlyStatus.RECONSUME_LATER){
                idempotencyHandler.reDelete(pointsDto.getUserId());
            }
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

    public void setIdempotencyHandler(IdempotencyHandler idempotencyHandler) {
        this.idempotencyHandler = idempotencyHandler;
    }
}
