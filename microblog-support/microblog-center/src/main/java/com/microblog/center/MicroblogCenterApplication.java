package com.microblog.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class MicroblogCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogCenterApplication.class,args);
    }
}