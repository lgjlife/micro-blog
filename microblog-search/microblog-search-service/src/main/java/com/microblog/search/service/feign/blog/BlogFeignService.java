package com.microblog.search.service.feign.blog;


import com.microblog.blog.dao.dto.BlogInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name="microblog-blog",configuration = BlogFeignConfig.class)
public interface BlogFeignService {

    @RequestMapping(value = "/blog/query/1",method = RequestMethod.GET)
    BlogInfoDto queryBlogfo(@RequestParam("blogId") Long blogId, @RequestParam("userId") Long userId);


}
