package com.microblog.authorization.exception;

/**
 *功能描述
 * @author lgj
 * @Description  认证次数超过限制
 * @date 　
*/
public class ExcessiveAttemptsException extends RuntimeException{

    public ExcessiveAttemptsException() {
        super();
    }

    public ExcessiveAttemptsException(String message) {
        super(message);
    }
}
