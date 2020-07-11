package com.microblog.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by Steven on 2019/10/27.
 */
@RestController
public class ApiController {

    @GetMapping("/api/needauth")
    public String needauth() {
        return "needauth  " + new Random().nextInt(1000);
    }


    @GetMapping("/api/notauth")
    public String notauth() {
        return "notauth " + new Random().nextInt(1000);
    }

}
