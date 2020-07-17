package com.microblog.buysbusiness.feign.goods;


import com.microblog.buysbusiness.feign.config.FeignConfig;
import com.microblog.util.result.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Component
@FeignClient(name="Microblog-Goods",configuration = FeignConfig.class,
        //fallback = PointsFeignServiceFallback.class
       fallbackFactory = GoodsFeignServiceFallbackFactory.class
         )
public interface GoodsFeignService {

    @RequestMapping(value = "/goods/query",method = RequestMethod.GET)
    WebResult query();


    @RequestMapping(value = "/goods/delete",method = RequestMethod.GET)
    WebResult delete();
}
