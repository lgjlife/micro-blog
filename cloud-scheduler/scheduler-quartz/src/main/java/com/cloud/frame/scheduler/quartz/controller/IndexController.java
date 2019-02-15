package com.cloud.frame.scheduler.quartz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 22:08
 **/

@Api("/index")
@Controller
@RequestMapping("/index")
public class IndexController {

    @ApiOperation(value="/",notes = "获取索引页面",httpMethod="GET")
    @GetMapping
    public  String index(){
        return "/admin/index";
    }

}
