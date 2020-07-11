package com.microblog.webflux.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;



@RestController
@Slf4j
@RequestMapping("/webflux")
public class WebFluxController {


    @RequestMapping("/data")
    public String data(){

        log.info("/data");
        return new Random().nextInt(100) + "";
    }

    @PostMapping("/data/update")
    public String updateData(){

        log.info("/data/update..........................................................................");
        return new Random().nextInt(100) + "";
    }


}
