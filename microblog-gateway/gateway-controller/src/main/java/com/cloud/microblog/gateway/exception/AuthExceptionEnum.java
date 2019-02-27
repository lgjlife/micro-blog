package com.cloud.microblog.gateway.exception;


public enum  AuthExceptionEnum {

    TOKEN_TIMEOUT(0,"Token 超时"),
    TOKEN_EMPTY(1,"Token 为空"),
    NO_PERMISSION(2,"没有权限")
    ;

    private int code;
    private String message;


    AuthExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
