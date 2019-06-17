package com.microblog.user.service.feign;


import com.microblog.user.dao.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name="microblog-points",configuration = FeignConfig.class)
public interface PointsFeignService {

    @GetMapping(value = "/points/query")
    User queryPoints(@RequestParam("userId") Long userId);




}
