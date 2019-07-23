package com.github.bloomfilter.filter;

import com.github.bloomfilter.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisBloomFilter implements BloomFilter {

    @Autowired
    private RedisClient redisClient;


    @Override
    public boolean add(String key, String value) {
        return redisClient.add(key,value);
    }

    @Override
    public boolean[] addMulti(String key, String... values) {
        return redisClient.addMulti(key,values);
    }

    @Override
    public boolean exists(String key, String value) {
        return redisClient.exists(key,value);
    }

    @Override
    public boolean[] existsMulti(String key, String... values) {
        return redisClient.existsMulti(key,values);
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
