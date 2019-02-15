package com.cloud.frame.consumerFeign.config.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 11:42
 **/
@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer(){
       return new Retryer.Default(100,SECONDS.toMillis(1),5);
    }
}
