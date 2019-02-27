package com.cloud.microblog.gateway.exception;

import com.netflix.zuul.exception.ZuulException;

public class UnAuthException extends ZuulException {


    AuthExceptionEnum authExceptionEnum;

    private int code;
    private String message;

   /* public UnAuthException(AuthExceptionEnum authExceptionEnum) {
        this.code = authExceptionEnum.getCode();
        this.message = authExceptionEnum.getMessage();
    }*/

    public UnAuthException(String sMessage, int nStatusCode, String errorCause) {
        super(sMessage, nStatusCode, errorCause);
    }
}
