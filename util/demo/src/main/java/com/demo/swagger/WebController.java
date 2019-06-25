package com.demo.swagger;


import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/demo")
public class WebController {


    @ApiOperation(value = "/1",notes="这是demo1",tags="{tag1,tag2}",response=String.class,httpMethod= "GET")
    @GetMapping("/1")
    public String demo1(){

        return  new Date().toString();
    }

    @ApiOperation(value = "/demoaaa")
    @GetMapping("/2")
    public String demo2(){

        return  new Date().toString();
    }
}
