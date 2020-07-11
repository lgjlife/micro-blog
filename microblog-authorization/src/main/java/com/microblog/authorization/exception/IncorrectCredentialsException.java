package com.microblog.authorization.exception;

/**
 *功能描述
 * @author lgj
 * @Description  不正确的凭证 　　　
 * @date 　
*/
public class IncorrectCredentialsException extends RuntimeException{

    public IncorrectCredentialsException() {
        super();
    }

    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
