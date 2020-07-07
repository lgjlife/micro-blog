package com.microblog.msgservice.service.impl;


import com.microblog.msgentity.SMSEntity;
import com.microblog.msgservice.exception.MsgSendFailException;
import com.microblog.msgservice.sender.DefaultSmsSender;
import com.microblog.msgservice.sender.SmsSender;
import com.microblog.msgservice.service.MsgSMSHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *功能描述
 * @author lgj
 * @Description 短信消息处理 　　　
 * @date 　
*/
@Data
@Slf4j
public class MsgSMSHandlerImpl extends MsgSMSHandler {

    private SmsSender smsSender = new DefaultSmsSender();

    @Override
    public void doHandler(SMSEntity smsEntity) throws MsgSendFailException {
        smsSender.send(smsEntity.getPhone(),smsEntity.getCode());
    }
}
