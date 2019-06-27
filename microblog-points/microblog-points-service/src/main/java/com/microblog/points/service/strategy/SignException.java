package com.microblog.points.service.strategy;


/**
 *功能描述
 * @author lgj
 * @Description  签到异常
 * @date 6/27/19
*/
public class SignException extends Exception {

    public SignException(String message) {
        super(message);
    }

    public SignException(String message, Throwable cause) {
        super(message, cause);
    }
}
