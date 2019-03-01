package com.cloud.microblog.user.service.rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="auth-app")
public interface TokenClient {

    @RequestMapping(path = "/token/{id}",method = RequestMethod.GET)
    String queryToken(@PathVariable("id") Long id);

}
