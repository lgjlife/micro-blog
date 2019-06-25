package com.microblog.consumer1.controller;


import com.microblog.consumer1.feign.ProviderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {


    @Autowired
    ProviderFeignService providerFeignService;

    @RequestMapping("/hello")
    public String hello(){

        log.info("hello ..... ");
        String result =  providerFeignService.hello();
        log.info("result = " + result);
        return  result;
    }

    @RequestMapping("/info")
    public String info(){

        log.info("info ..... ");
        String result =  providerFeignService.info();
        log.info("result = " + result);
        return  result;
    }
}