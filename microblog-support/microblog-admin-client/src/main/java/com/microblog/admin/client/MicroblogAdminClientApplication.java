package com.microblog.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class MicroblogAdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogAdminClientApplication.class, args);
    }

}
