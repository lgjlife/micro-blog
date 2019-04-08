package com.microblog.cloudadminclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudAdminClientApplication {

    public static void main(String[] args) {

        DispatcherServlet
        SpringApplication.run(CloudAdminClientApplication.class, args);
    }
}
