package com.cloud.microblog.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {


    public static void main(String args[]){
        SpringApplication.run(AuthApplication.class,args);
    }
}
