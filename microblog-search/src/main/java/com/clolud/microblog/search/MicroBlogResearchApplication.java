package com.clolud.microblog.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages = {"com.cloud.microblog.common.aop.syslog.aspect","com.clolud.microblog.search"})
@SpringBootApplication()//exclude = DataSourceAutoConfiguration.class
public class MicroBlogResearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroBlogResearchApplication.class, args);
    }

}

