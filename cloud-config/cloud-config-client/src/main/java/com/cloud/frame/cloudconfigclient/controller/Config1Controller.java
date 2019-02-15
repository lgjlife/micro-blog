package com.cloud.frame.cloudconfigclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-17 00:23
 **/

@RestController
@RequestMapping("/config")
public class Config1Controller {

    private static final Logger log = LoggerFactory.getLogger(Config1Controller.class);

    @Value("${data}")
    private String data;

    @GetMapping("/name1")
    public String name(){

        log.info("访问ConfigController name1 = " + data);
        return "配置中心的name = " + data;
    }
}
