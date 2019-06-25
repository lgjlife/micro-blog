package com.microblog.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroblogWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogWebsocketApplication.class, args);
    }

}
