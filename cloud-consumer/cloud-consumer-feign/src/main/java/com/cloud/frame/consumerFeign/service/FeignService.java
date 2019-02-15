package com.cloud.frame.consumerFeign.service;

import com.cloud.frame.consumerFeign.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 11:25
 **/
@Component
@FeignClient(name = "cloud-provider",configuration = FeignConfig.class)
public interface FeignService {

    @RequestMapping("/feign")
     String feign();
}
