package com.microblog.user.service.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PointsFeignServiceFallbackFactory implements FallbackFactory<PointsFeignService> {

    @Override
    public PointsFeignService create(Throwable throwable) {
        return new PointsFeignService(){

            @Override
            public Long queryPoints(Long userId) {

                log.error("Fallback:QueryPoints[userId:{}] request Service[Points] fail! Throwable is :{} ",userId,throwable.getMessage());
                return 0L;
            }
        };
    }
}
