package com.microblog.blog.service.auth;

import com.microblog.common.zk.ZkCli;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BlogAuthAutoConfiguration {

    @Bean
    public ZkCli zkCli(){
        ZkCli zkCli = new ZkCli();
        zkCli.connect("localhost:2181");
        return zkCli;
    }

}
