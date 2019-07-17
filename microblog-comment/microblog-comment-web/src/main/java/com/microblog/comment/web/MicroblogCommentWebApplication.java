package com.microblog.comment.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("com.microblog.commmet.dao.mapper")
@ComponentScan(value = {"com.microblog.comment"})
@SpringBootApplication
public class MicroblogCommentWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogCommentWebApplication.class, args);
    }

}
