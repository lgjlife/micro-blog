package com.microblog.authorization.exception;

/**
 *功能描述 
 * @author lgj
 * @Description 　未知的账号　　
 * @date 　
*/
public class UnknownAccountException extends RuntimeException{

    public UnknownAccountException() {
        super();
    }

    public UnknownAccountException(String message) {
        super(message);
    }
}
