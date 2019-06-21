package com.microblog.provider.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/provider")
public class ProviderController {


    @RequestMapping("/hello")
    public String hello(){

        log.info("hello ..... ");
        return  "Provider  Hello !!!" + new Date().toString();
    }

    @RequestMapping("/info")
    public String info(){

        log.info("info ..... ");
        return  "Provider  info !!!" + new Date().toString();
    }
}
