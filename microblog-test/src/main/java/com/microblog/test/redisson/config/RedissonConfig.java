package com.microblog.test.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

public class RedissonConfig {
    public void init(){

        Config config = new Config();


        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

       /* config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:6379");*/

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);

        // Reactive API
        RedissonReactiveClient redissonReactive = Redisson.createReactive(config);

        // RxJava2 API
        RedissonRxClient redissonRx = Redisson.createRx(config);



        RLock lock = redisson.getLock("lock");

        lock.lock();
        lock.lock();
        lock.lock();



        lock.unlock();
    }

    public static void main(String args[]){

        RedissonConfig config = new RedissonConfig();
        config.init();
    }
}
