package com.microblog.msgservice.sender;


import com.microblog.msgservice.exception.MsgSendFailException;

public abstract class SmsSender {

    public abstract void send(String phone,Object content) throws MsgSendFailException;
}
