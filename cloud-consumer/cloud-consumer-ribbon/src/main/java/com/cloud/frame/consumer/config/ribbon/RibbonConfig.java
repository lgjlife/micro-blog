package com.cloud.frame.consumer.config.ribbon;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: cloud-parent
 * @description: Ribbon配置类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 00:36
 **/

@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
