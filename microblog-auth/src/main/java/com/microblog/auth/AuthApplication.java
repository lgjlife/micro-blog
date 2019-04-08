package com.microblog.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.microblog.auth.*", "com.microblog.common"})
public class AuthApplication {


    public static void main(String args[]){
        SpringApplication.run(AuthApplication.class,args);
    }
}
