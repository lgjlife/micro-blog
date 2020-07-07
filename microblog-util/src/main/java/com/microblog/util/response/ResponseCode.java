package com.microblog.util.response;

public enum  ResponseCode {

    SUCCESS(0,"SUCCEEE"),
    FAIL(1,"FAIL"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
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
