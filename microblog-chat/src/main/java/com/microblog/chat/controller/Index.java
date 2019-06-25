package com.microblog.chat.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/index")
public class Index {


    @RequestMapping
    public String index() {
        log.info("访问  /index/");
        return "index";
    }
}
