package com.microblog.gateway.zk;


import com.microblog.common.zk.ZkCli;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkConfiguration {

    @Bean
    public ZkCli zkCli(){
        ZkCli zkCli = new ZkCli();
        zkCli.connect("localhost:2181");
        return zkCli;
    }
}
