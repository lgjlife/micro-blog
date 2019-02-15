package com.cloud.frame.consumerFeign.controller;

import com.cloud.frame.consumerFeign.aop.syslog.anno.PrintUrlAnno;
import com.cloud.frame.consumerFeign.service.serviceImpl.feign.FeignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-12 21:13
 **/

@RestController
public class ConsumerController {

    @Autowired
    private FeignServiceImpl feignService;


    @PrintUrlAnno
    @GetMapping("/index")
    public String index(){
        return "服务消费者 /index   " + new Random().nextInt(100);
    }

    @PrintUrlAnno
    @GetMapping("/feign")
    public String feign(){
        return feignService.feign();
    }

}
