package com.microblog.cache.redis.config;


import lombok.Data;

@Data
public class ClusterProperties {

    private String[] nodes;
    private int connectionTimeout = 5000;
    private int soTimeout;
    private int maxAttempts = 3;
    private String password;
    private String clientName;

    private int maxTotal = 8;
    private int maxIdle = 8;
    private int minIdle = 0;



}
