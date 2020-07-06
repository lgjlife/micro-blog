package com.microblog.msgservice.sender;

import com.microblog.msgentity.MailEntity;
import com.microblog.msgservice.exception.MsgSendFailException;

public abstract class MailSender {

    public abstract void send(MailEntity mailEntity) throws MsgSendFailException;
}
