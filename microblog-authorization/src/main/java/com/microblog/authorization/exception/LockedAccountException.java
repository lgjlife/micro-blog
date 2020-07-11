package com.microblog.authorization.exception;

/**
 *功能描述
 * @author lgj
 * @Description  账号被锁定　　　
 * @date 　
*/
public class LockedAccountException extends RuntimeException{

    public LockedAccountException() {
        super();
    }

    public LockedAccountException(String message) {
        super(message);
    }
}
