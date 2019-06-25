package com.microblog.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroblogGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogGateApplication.class, args);
    }

}
