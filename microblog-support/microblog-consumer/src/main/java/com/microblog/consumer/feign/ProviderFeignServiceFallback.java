package com.microblog.consumer.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ProviderFeignServiceFallback implements ProviderFeignService {

    @Override
    public String hello() {
        return "Can Not Requerst!";
    }


}
