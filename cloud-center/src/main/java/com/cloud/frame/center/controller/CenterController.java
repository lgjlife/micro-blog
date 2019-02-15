package com.cloud.frame.center.controller;

import com.cloud.frame.center.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: top-parent
 * @description: 注册中心Controller
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-18 18:04
 **/

@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    CenterService centerService;

    @GetMapping("/service/list")
    public String serviceList(){

        return centerService.getServiceList();
    }
}
