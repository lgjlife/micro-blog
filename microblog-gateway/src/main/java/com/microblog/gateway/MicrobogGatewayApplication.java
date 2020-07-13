package com.microblog.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MicrobogGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrobogGatewayApplication.class, args);
    }

}
