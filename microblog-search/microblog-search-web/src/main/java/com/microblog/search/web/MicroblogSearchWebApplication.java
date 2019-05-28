package com.microblog.search.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(basePackages={"com.microblog.search","com.microblog.common"})
@SpringBootApplication
public class MicroblogSearchWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogSearchWebApplication.class, args);
    }

}