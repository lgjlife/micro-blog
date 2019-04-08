package com.clolud.microblog.search.service.feign;

import com.microblog.user.dao.model.UserDelete;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Component
@FeignClient(name="microblog-user",configuration = FeignConfig.class)
public interface UserDeleteFeignService {

    @RequestMapping(value = "/user-delete",method = RequestMethod.GET)
    List<UserDelete> query();

    @RequestMapping(value = "/user-delete",method = RequestMethod.DELETE)
    void delete(List<Long>  uids);
}
