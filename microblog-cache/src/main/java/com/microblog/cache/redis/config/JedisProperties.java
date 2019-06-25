package com.microblog.cache.redis.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "jedis")
public class JedisProperties {

    private ClusterProperties cluster;





}
