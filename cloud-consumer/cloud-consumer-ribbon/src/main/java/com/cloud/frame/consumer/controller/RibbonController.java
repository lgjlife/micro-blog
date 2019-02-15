package com.cloud.frame.consumer.controller;

import com.cloud.frame.consumer.aop.syslog.anno.PrintUrlAnno;
import com.cloud.frame.consumer.service.serviceImpl.ribbon.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-12 23:27
 **/

@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @PrintUrlAnno
    @GetMapping("/ribbon")
    public String ribbon(){
        return ribbonService.ribbon();
    }


}
