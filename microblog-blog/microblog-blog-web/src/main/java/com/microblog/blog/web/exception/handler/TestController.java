package com.microblog.blog.web.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @RequestMapping("/info1")
    public String test(){
      log.info("/user/info1");

      throw new NullPointerException("TestController have exception");

    }
}
