package com.cloud.frame.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 02:49
 **/

@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixdashboardApplication {
    public static void main(String[] args) {

        SpringApplication.run(HystrixdashboardApplication.class,args);
    }
}
