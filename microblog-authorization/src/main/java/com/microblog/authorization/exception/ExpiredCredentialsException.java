package com.microblog.authorization.exception;

/**
 *功能描述
 * @author lgj
 * @Description  凭证过期　　　
 * @date 　
*/
public class ExpiredCredentialsException extends RuntimeException{

    public ExpiredCredentialsException() {
        super();
    }

    public ExpiredCredentialsException(String message) {
        super(message);
    }
}
