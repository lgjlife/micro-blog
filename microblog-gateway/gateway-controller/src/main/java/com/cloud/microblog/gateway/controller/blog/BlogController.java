package com.cloud.microblog.gateway.controller.blog;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.code.BlogReturnCode;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.result.WebResult;
import com.cloud.microblog.gateway.dto.BlogInfoDto;
import com.cloud.microblog.gateway.service.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    BlogService blogService;

    @PrintUrlAnno
    @PostMapping("/list")
    public BaseResult queryBlog(@RequestBody Map<String,Object>  map){

       // String type = (String) map.get("type");
      //  int start = (Integer) map.get("start");
       // int end= (Integer)map.get("limit");

        log.debug(map.values().toString());

         List<BlogInfoDto> blogInfoDtos = blogService.queryBlog("ALL",0,10);

         BaseResult result = new WebResult(BlogReturnCode.BLOG_QUERY_SUCCESS,blogInfoDtos);

         return result;
    }

}
