package com.microblog.config.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableConfigurationProperties(Properties.class)
public class UserConfig {


    @Bean
    public User user(){
        return new User();
    }
}
