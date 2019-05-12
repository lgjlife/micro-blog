package com.microblog.msg.message.handler;

import com.microblog.common.dto.MailDto;
import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.JdkSerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.*;


/**
 *功能描述
 * @author lgj
 * @Description  邮件发送处理类
 * @date 5/12/19
*/
@Slf4j
public class EmailMessageHandler implements MessageHandler{

    private AbstractSerialize serialize = new JdkSerializeUtil();
    private RedisTemplate redisTemplate;

    public EmailMessageHandler(RedisTemplate redisTemplate) {
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

        log.debug("Email:The msgs size is {}",msgs.size() );

        Future<ConsumeConcurrentlyStatus> future = null;
        for(MessageExt msg:msgs){
            byte[] body =  msg.getBody();
            MailDto mailDto = serialize.deserialize(body,null);
            future =  taskExecutor.submit(new EmailMessageHandler.EmailSendHandle(mailDto));
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
     * @Description  执行具体发送邮件的线程
     * @date 5/12/19
     */
    private class EmailSendHandle implements Callable{

        private MailDto mailDto;

        public EmailSendHandle(MailDto mailDto) {
            this.mailDto = mailDto;
        }

        @Override
        public Object call() throws Exception {

            long curTimestamp = System.currentTimeMillis();

            if(mailDto.getEffectiveTimeMs() != null){
                //非null 说明设置有效时间
                if((curTimestamp - mailDto.getTimeStamp()) > mailDto.getEffectiveTimeMs()){
                    //超过邮件的有效时间,丢弃消息，返回消费成功
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }


            }

            try{
                log.debug("发送短信：{}",mailDto);
                //调用发邮件操作
                //消息幂等处理
                // SmsUtil.sendMsg(smsDto.getPhone(),smsDto.getCode());
                String value = (String)redisTemplate.opsForValue().get(mailDto.getId());
                if(value == null){
                    //消息未消费过
                    try{

                        // SmsUtil.sendMsg(smsDto.getPhone(),smsDto.getCode());
                        //这里要是写入失败怎么办????
                        redisTemplate.opsForValue().set(mailDto.getId(),"",mailDto.getEffectiveTimeMs(),TimeUnit.MICROSECONDS);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    catch(Exception ex){
                        log.error(ex.getMessage());
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                }
                //消息消费过,直接返回消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            catch(Exception ex){
                log.error("Send email[from:{} to: {}] fail !{}",mailDto.getTitle(),mailDto.getTo(),ex.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;

            }
        }


    }
}
