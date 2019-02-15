package com.cloud.frame.consumer.controller;

import com.cloud.frame.consumer.aop.syslog.anno.PrintUrlAnno;
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

    @PrintUrlAnno
    @GetMapping("/index")
    public String index(){
        return "服务消费者 /index   " + new Random().nextInt(100);
    }
}
