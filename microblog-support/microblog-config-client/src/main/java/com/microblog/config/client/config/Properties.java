package com.microblog.config.client.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
//@ConfigurationProperties
public class Properties {

    @Value("${data}")
    private String data;

    @Value("${name}")
    private String name;
}
