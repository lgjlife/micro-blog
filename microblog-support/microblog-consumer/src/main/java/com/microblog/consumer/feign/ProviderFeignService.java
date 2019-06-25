package com.microblog.consumer.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Component
@FeignClient(name="microblog-provider",configuration = FeignConfig.class,
       fallback = ProviderFeignServiceFallback.class
         )
public interface ProviderFeignService {

    @RequestMapping(value = "/provider/hello",method = RequestMethod.GET)
    String  hello();
}
