package com.microblog.buysbusiness.feign.cart;


import com.microblog.buysbusiness.feign.config.FeignConfig;
import com.microblog.util.response.ServerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Component
@FeignClient(name="Microblog-Cart",configuration = FeignConfig.class,
        //fallback = PointsFeignServiceFallback.class
       fallbackFactory = CartFeignServiceFallbackFactory.class
         )
public interface CartFeignService {

    @RequestMapping(value = "/cart/query",method = RequestMethod.GET)
    ServerResponseDto query();


    @RequestMapping(value = "/cart/delete",method = RequestMethod.GET)
    ServerResponseDto delete();

}
