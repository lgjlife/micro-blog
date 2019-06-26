package com.microblog.gate.auth;

import com.microblog.common1.zk.ZkCli;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ZookeeperAutoConfiguration {

    @Bean
    public ZkCli zkCli(){
        ZkCli zkCli = new ZkCli();
        zkCli.connect("172.17.0.1:2181");
        return zkCli;
    }

}