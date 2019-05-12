package com.microblog.msg.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {

    private String host = "127.0.0.1";
    private int port = 6379;

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port);
        //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});

        return Redisson.create(config);

    }
}
