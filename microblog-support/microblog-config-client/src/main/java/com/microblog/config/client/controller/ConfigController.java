package com.microblog.config.client.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope // curl -X POST http://localhost:8004/actuator/refresh
@RestController
@RequestMapping("/config")
public class ConfigController {


    @Value("${data}")
    private String data;

    @Value("${name}")
    private String name;

    @RequestMapping("/name")
    public String name(){
        log.info("name = " + name );
        return name;
    }

    @RequestMapping("/data")
    public String data(){
        log.info("data = " + data );
        return data;
    }
}
