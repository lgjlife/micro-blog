package com.microblog.cache.redis.exception;

public class RedisAddressException extends RuntimeException {

    public RedisAddressException(String message) {
        super(message);
    }

    public RedisAddressException(String message, Throwable cause) {
        super(message, cause);
    }
}
