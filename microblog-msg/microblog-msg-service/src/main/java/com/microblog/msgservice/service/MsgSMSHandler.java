package com.microblog.msgservice.service;

import com.microblog.msgentity.SMSEntity;
import com.microblog.msgservice.exception.MsgSendFailException;

/**
 *功能描述
 * @author lgj
 * @Description  短信消息处理　　　
 * @date 　
*/
public abstract class MsgSMSHandler implements MsgHandler{


     @Override
     public void handler(Object msg) throws MsgSendFailException {
          this.doHandler((SMSEntity)msg);
     }

     public abstract void doHandler(SMSEntity smsEntity) throws MsgSendFailException;
}
