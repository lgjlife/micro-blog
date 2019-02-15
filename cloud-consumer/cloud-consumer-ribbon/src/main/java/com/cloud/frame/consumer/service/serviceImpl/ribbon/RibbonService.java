package com.cloud.frame.consumer.service.serviceImpl.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 00:39
 **/

@Service
public class RibbonService {

    private  static Logger log = LoggerFactory.getLogger(RibbonService.class);
    @Autowired
    private  RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "ribbonFail")
    public String  ribbon(){
        return  restTemplate.getForObject("http://cloud-provider/ribbon",String.class);
    }
    public String  ribbonFail(){
        log.info("ribbon 请求失败");
        return  "ribbon 请求失败" + new Date().getSeconds();
    }

}
