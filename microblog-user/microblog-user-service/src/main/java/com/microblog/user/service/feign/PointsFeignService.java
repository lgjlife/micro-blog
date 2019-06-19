package com.microblog.user.service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name="microblog-points",configuration = FeignConfig.class,
        //fallback = PointsFeignServiceFallback.class
       fallbackFactory = PointsFeignServiceFallbackFactory.class
         )
public interface PointsFeignService {

    @RequestMapping(value = "/points/query",method = RequestMethod.GET)
    Long queryPoints(@RequestParam Long userId);
}
