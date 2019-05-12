package com.microblog.msg.message.handler;

import com.microblog.common.dto.SMSDto;
import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.JdkSerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.*;


/**
 *功能描述 
 * @author lgj
 * @Description  处理mq中的短信消息
 * @date 5/12/19
*/
@Slf4j
public class SMSMessageHandler implements MessageHandler {

    private AbstractSerialize serialize = new JdkSerializeUtil();

    private RedisTemplate redisTemplate;

    private RedissonClient redissonClient;


    public SMSMessageHandler(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /***
     * 执行任务的线程池
     */
    private ExecutorService taskExecutor  = new ThreadPoolExecutor(100,
            100,
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public ConsumeConcurrentlyStatus handler(List<MessageExt> msgs) {

        log.debug("sms:The msgs size is {}",msgs.size() );

        Future<ConsumeConcurrentlyStatus> future = null;
        for(MessageExt msg:msgs){
            byte[] body =  msg.getBody();
            SMSDto smsDto = serialize.deserialize(body,null);
            future =  taskExecutor.submit(new SMSSendHandle(smsDto));
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
     * @Description  执行具体发送短信的线程
     * @date 5/12/19
    */
    private class SMSSendHandle implements Callable{

        private SMSDto smsDto;

        public SMSSendHandle(SMSDto smsDto) {
            this.smsDto = smsDto;
        }

        @Override
        public Object call() throws Exception {

            long curTimestamp = System.currentTimeMillis();

            if(smsDto.getEffectiveTimeMs() != null){
                //非null 设置有效时间
                if((curTimestamp - smsDto.getTimeStamp()) > smsDto.getEffectiveTimeMs()){
                    //超过短信的有效时间,丢弃消息，返回消费成功
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }
            }

            try{
                log.debug("发送短信：{}",smsDto);
                //调用发短信　操作
                //消息幂等处理

                long id = smsDto.getId();
                RLock lock = redissonClient.getLock("lock:sms");
                String value = null;
                try{
                    lock.lock(20,TimeUnit.SECONDS);
                    value = (String)redisTemplate.opsForValue().get(id);
                    if(value == null){
                        //消息未消费过
                        redisTemplate.opsForValue().set(id,"",smsDto.getEffectiveTimeMs(),TimeUnit.MILLISECONDS);
                    }
                }
                catch(Exception ex){
                    log.error(ex.getMessage());
                }
                finally{
                    lock.unlock();
                }

                if(value == null){
                    log.debug("消息未被消费过");
                    //消息未消费过
                    try{
                        // SmsUtil.sendMsg(smsDto.getPhone(),smsDto.getCode());
                        //这里要是写入失败怎么办????
                       return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    catch(Exception ex){
                        log.error(ex.getMessage());
                        //消息消费失败，需要把redis中的消息删除
                        redisTemplate.delete(id);
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                }
                //消息消费过,直接返回消费成功
                log.debug("消息已经消费过");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;


            }
            catch(Exception ex){
                log.error("Send SMS[{}-{}] fail !{}",smsDto.getPhone(),smsDto.getCode(),ex.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;

            }
        }


    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
}
