package com.cloud.frame.consumerFeign.service.serviceImpl.feign;

import com.cloud.frame.consumerFeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 11:46
 **/
@Service
public class FeignServiceImpl {

    @Autowired
    FeignService feignService;

    public  String feign(){
        return feignService.feign();
    }
}
