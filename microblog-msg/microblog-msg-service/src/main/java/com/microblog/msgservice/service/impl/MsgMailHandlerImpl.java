package com.microblog.msgservice.service.impl;

import com.microblog.msgentity.MailEntity;
import com.microblog.msgservice.exception.MsgSendFailException;
import com.microblog.msgservice.sender.MailSender;
import com.microblog.msgservice.service.MsgMailHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MsgMailHandlerImpl extends MsgMailHandler {

    private MailSender mailSender;

    public MsgMailHandlerImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void doHandler(MailEntity entity) throws MsgSendFailException {
        mailSender.send(entity);
    }
}
