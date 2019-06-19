package com.microblog.user.service.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PointsFeignServiceFallbackFactory implements FallbackFactory<PointsFeignService> {

    @Override
    public PointsFeignService create(Throwable throwable) {

        log.error("QueryPoints[{}] request Service[Points] fail!",throwable.getMessage());

        return new PointsFeignService(){

            @Override
            public Long queryPoints(Long userId) {

                log.error("QueryPoints[{}] request Service[Points] fail!",userId);
                return 0L;
            }
        };
    }
}
