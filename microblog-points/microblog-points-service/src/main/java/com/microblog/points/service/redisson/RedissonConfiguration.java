package com.microblog.points.service.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {

    private String host = "172.17.0.1";
    private int port = 6379;

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://172.18.0.1:8202","redis://172.18.0.1:8203","redis://172.18.0.1:8204",
                "redis://172.18.0.1:8205","redis://172.18.0.1:8206","redis://172.18.0.1:8207");
        //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});

        return Redisson.create(config);

    }
}
