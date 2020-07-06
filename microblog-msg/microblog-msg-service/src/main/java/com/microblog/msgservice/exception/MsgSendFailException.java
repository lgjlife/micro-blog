package com.microblog.msgservice.exception;

public class MsgSendFailException extends Exception {

    public MsgSendFailException() {
    }

    public MsgSendFailException(String message) {
        super(message);
    }

    public MsgSendFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
