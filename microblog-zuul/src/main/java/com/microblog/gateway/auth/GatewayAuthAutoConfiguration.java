package com.microblog.gateway.auth;

import com.microblog.common.zk.ZkCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayAuthAutoConfiguration {

    @Autowired
    private ZkCli zkCli;

    @Bean
    public AuthFilterService authFilterService(){

        AuthFilterService authFilterService = new AuthFilterService();
        authFilterService.setZkCli(zkCli);
        authFilterService.setListen();

        return  authFilterService;

    }
}
