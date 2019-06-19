package com.microblog.user.service.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PointsFeignServiceFallback implements PointsFeignService {


    @Override
    public Long queryPoints( Long userId) {

        log.error("QueryPoints[{}] request Service[Points] fail!",userId);
        return 0L;
    }
}
