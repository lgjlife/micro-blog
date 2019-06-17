package com.microblog.points.web.exception;

public class UserUnloginException extends Exception {


    public UserUnloginException(String message) {
        super(message);
    }

    public UserUnloginException(String message, Throwable cause) {
        super(message, cause);
    }
}
