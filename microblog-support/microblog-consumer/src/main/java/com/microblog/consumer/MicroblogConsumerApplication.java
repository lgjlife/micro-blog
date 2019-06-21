package com.microblog.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//http://localhost:8302/actuator/hystrix.stream
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.microblog.consumer.feign")
@SpringBootApplication
public class MicroblogConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(MicroblogConsumerApplication.class, args);
    }

}
