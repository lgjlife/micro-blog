package com.microblog.cache.redis.config;

import com.microblog.cache.redis.cluster.RedisCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JedisProperties.class)
public class JedisAutoconfiguration {

    @Autowired
    private JedisProperties jedisProperties;

    @ConditionalOnProperty(prefix="jedis",name = {"cluster"})
    public RedisCluster redisCluster(){
        RedisCluster redisCluster = new RedisCluster(jedisProperties);
        redisCluster.connect();
        return redisCluster;

    }
}
