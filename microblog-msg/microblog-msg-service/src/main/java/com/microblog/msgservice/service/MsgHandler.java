package com.microblog.msgservice.service;

import com.microblog.msgservice.exception.MsgSendFailException;

public interface MsgHandler {

    void handler(Object msg) throws MsgSendFailException;
}
