package com.microblog.msgservice.service;

import com.microblog.msgentity.MailEntity;
import com.microblog.msgservice.exception.MsgSendFailException;

/**
 *功能描述 
 * @author lgj
 * @Description  邮件消息处理　　　
 * @date 　
*/
public abstract class MsgMailHandler implements MsgHandler{


    @Override
    public void handler(Object msg) throws MsgSendFailException {

       this.doHandler((MailEntity)msg);
    }

    public abstract void doHandler(MailEntity entity) throws MsgSendFailException;

}
