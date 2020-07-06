package com.microblog.msgservice.rocketmq;

import com.microblog.msgentity.MailEntity;
import com.microblog.msgentity.MsgTag;
import com.microblog.msgentity.SMSEntity;
import com.microblog.msgservice.service.MsgMailHandler;
import com.microblog.msgservice.service.MsgSMSHandler;
import com.utils.serialization.AbstractSerialize;
import com.utils.serialization.HessianSerializeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *功能描述 
 * @author lgj
 * @Description  rocket 消息处理　　
 * @date 　
*/

@Data
@Slf4j
public class MsgMessageListener implements MessageListenerConcurrently {

    private AbstractSerialize serialize = new HessianSerializeUtil();
    private Map<String,Class> serializeClass = new HashMap<>();

    private MsgSMSHandler msgSmsHandler;
    private MsgMailHandler msgMailHandler;

    public MsgMessageListener() {

        serializeClass.put(MsgTag.SMS_TAG,SMSEntity.class);
        serializeClass.put(MsgTag.MAIL_TAG,MailEntity.class);
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext ctx) {

        for(MessageExt messageExt:msgList){
            if(MsgTag.SMS_TAG==messageExt.getTags()){
                SMSEntity smsEntity =  (SMSEntity)serialize.deserialize(messageExt.getBody(),serializeClass.get(MsgTag.SMS_TAG));

                try{
                    msgSmsHandler.handler(smsEntity);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                catch(Exception ex){
                    log.error(ex.getMessage());

                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }

            }
            else if(MsgTag.MAIL_TAG==messageExt.getTags()){
                MailEntity smsEntity =  (MailEntity)serialize.deserialize(messageExt.getBody(),serializeClass.get(MsgTag.MAIL_TAG));

                try{

                    msgMailHandler.handler(smsEntity);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                   
                }
                catch(Exception ex){
                    log.error(ex.getMessage());
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }


}
