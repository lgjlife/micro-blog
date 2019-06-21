package com.microblog.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class MicroblogConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogConfigServerApplication.class, args);
    }

}
