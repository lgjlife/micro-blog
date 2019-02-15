package com.cloud.frame.provider.controller;

import com.cloud.frame.provider.aop.syslog.anno.PrintUrlAnno;
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
public class ProviderController {

    @PrintUrlAnno
    @GetMapping("/index")
    public String index(){

        return "服务提供者 /index   " + new Random().nextInt(100);
    }


    @PrintUrlAnno
    @GetMapping("/ribbon")
    public String ribbon(){

        return "服务提供者 /ribbon   " + new Random().nextInt(100);
    }


    @PrintUrlAnno
    @GetMapping("/feign")
    public String feign(){

        return "服务提供者 /feign   " + new Random().nextInt(100);
    }

}
