package com.microblog.authorization.exception;

/**
 *功能描述 
 * @author lgj
 * @Description  禁用的账号　　　
 * @date 　
*/
public class DisabledAccountException extends RuntimeException{

    public DisabledAccountException() {
        super();
    }

    public DisabledAccountException(String message) {
        super(message);
    }
}
