package com.cloud.microblog.user.service.config.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisStringUtil {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public  void  set(String key,Object value,long timeout ){

        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.MINUTES);

       // Object
    }


    public  Object  get(String key){
        return  redisTemplate.opsForValue().get(key);

    }

}
