package com.microblog.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MicroblogProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogProviderApplication.class, args);
    }

}
