package com.microblog.msg.message.handler;

import com.microblog.common.dto.MailDto;
import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.JdkSerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class EmailMessageHandler implements MessageHandler{

    private AbstractSerialize serialize = new JdkSerializeUtil();

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
     * @Description  执行具体发送短信的线程
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
                //null 设置有效时间
                if((curTimestamp - mailDto.getTimeStamp()) > mailDto.getEffectiveTimeMs()){
                    //超过短信的有效时间,丢弃消息，返回消费成功
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }


            }

            try{
                log.debug("发送短信：{}",mailDto);
                //调用发短信操作
                // SmsUtil.sendMsg(smsDto.getPhone(),smsDto.getCode());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            catch(Exception ex){
                log.error("Send email[from:{} to: {}] fail !{}",mailDto.getTitle(),mailDto.getTo(),ex.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;

            }
        }


    }
}
