package com.github.bloomfilter.redis;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisClientAutoconfiguration {

    @Bean
    public RedisClient redisClusterClient(){
        RedisClusterClient clusterClient = new RedisClusterClient();
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("172.18.0.1",8202));
        nodes.add(new HostAndPort("172.18.0.1",8203));
        nodes.add(new HostAndPort("172.18.0.1",8204));
        nodes.add(new HostAndPort("172.18.0.1",8205));
        nodes.add(new HostAndPort("172.18.0.1",8206));
        nodes.add(new HostAndPort("172.18.0.1",8207));
        clusterClient.setNodes(nodes);
        clusterClient.init();

        return clusterClient;

    }



}
