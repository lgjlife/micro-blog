package com.microblog.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-10 17:13
 **/

@EnableScheduling
@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.microblog.gateway.*", "com.microblog.common"})
public class GatewayApplication  {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class,args);
    }
}
