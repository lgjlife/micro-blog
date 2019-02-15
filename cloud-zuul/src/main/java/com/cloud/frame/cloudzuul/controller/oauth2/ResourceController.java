package com.cloud.frame.cloudzuul.controller.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Random;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-10 17:45
 **/

@RestController
public class ResourceController {

    private static Logger logger =  LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    ResourceServerTokenServices tokenServices;

    BearerTokenExtractor tokenExtractor = new BearerTokenExtractor();
    @RequestMapping("/user")
    public  Principal  user(ServletRequest request){
        logger.info("/user");

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Authentication authentication = tokenExtractor.extract(httpServletRequest);

        String token = (String) authentication.getPrincipal();


        return  tokenServices.loadAuthentication(token);

      //  return  "受限制的访问   " + new Random().nextInt(100);
    }

    @RequestMapping("/role")
    public  String  role(){
        logger.info("/role");
        return  "受限制的访问--role  " + new Random().nextInt(100);
    }


    @RequestMapping("/principal")
    public  Principal  principal(Principal user){
        logger.info("/principal");
        return  user;
    }

    @RequestMapping("/vistor")
    public  String  vistor(Principal user){
        logger.info("/vistor");
        if(user == null){
            logger.info("user is null");
        }
        return  "资源 vistor";
    }

}
