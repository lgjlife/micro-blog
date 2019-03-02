package com.cloud.microblog.auth.controller.token;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.token.jwt.JWTClaimsKey;
import com.cloud.microblog.common.token.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {


    /**
     *功能描述
     * @author lgj
     * @Description  创建JWT
     * @date 3/2/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @GetMapping(path = "/{id}")
    public  String queryToken(@PathVariable("id") Long id){

        Map<String,String> claims = new HashMap<>();
        claims.put(JWTClaimsKey.userId,String.valueOf(id));
        String  token =  JWTUtil.createJwt(claims);
        log.debug("id = {},token = {} ",token);

        try{
            boolean result = JWTUtil.verify(token);
            log.debug("JWTUtil.verify result = {}",result);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return  token;
    }





}
