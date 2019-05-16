package com.microblog.scheduler.service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@Component
@FeignClient(name="microblog-user",configuration = FeignConfig.class)
public interface BlogLikeService {

   /* @RequestMapping(value = "/user/info/query",method = RequestMethod.GET)
    User queryUserInfo(@RequestParam("userId") Long userId);*/


}
