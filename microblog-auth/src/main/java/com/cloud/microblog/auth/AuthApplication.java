package com.cloud.microblog.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.microblog.auth.*","com.cloud.microblog.common"})
public class AuthApplication {


    public static void main(String args[]){
        SpringApplication.run(AuthApplication.class,args);
    }
}
