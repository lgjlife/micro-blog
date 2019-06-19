package com.microblog.user.service.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@Slf4j
public class PointsFeignServiceFallback implements PointsFeignService {


    @Override
    public Long queryPoints(@RequestParam Long userId) {

        log.error("QueryPoints[{}] request Service[Points] fail!",userId);
        return 0L;
    }
}
