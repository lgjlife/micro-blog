package com.cloud.microblog.auth.controller.token;


import com.cloud.microblog.auth.jwt.JWTUtil;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {


    @PrintUrlAnno
    @RequestMapping("/check")
    public String check(){


        return  "check  token ";
    }

    @PrintUrlAnno
    @GetMapping(path = "/{id}")
    String queryToken(@PathVariable("id") Long id){

        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",id);
        String  token =  JWTUtil.createJwt(claims);
        log.debug("token = {} ",token);
        return  token;
    }
}
