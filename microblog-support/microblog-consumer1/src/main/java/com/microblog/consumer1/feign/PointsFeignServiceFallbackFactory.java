package com.microblog.consumer1.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PointsFeignServiceFallbackFactory implements FallbackFactory<ProviderFeignService> {

    @Override
    public ProviderFeignService create(Throwable throwable) {
        return new ProviderFeignService(){

            @Override
            public String hello() {
                return "Can Not Requerst!";
            }

            @Override
            public String info() {
                return "Can Not Requerst!";
            }
        };
    }
}
