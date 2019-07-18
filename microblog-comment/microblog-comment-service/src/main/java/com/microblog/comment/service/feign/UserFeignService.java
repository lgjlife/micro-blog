package com.microblog.comment.service.feign;


import com.microblog.user.dao.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name="microblog-user",configuration = FeignConfig.class)
public interface UserFeignService {

    @RequestMapping(value = "/user/info/query",method = RequestMethod.GET)
    User queryUserInfo(@RequestParam("userId") Long userId);


}
