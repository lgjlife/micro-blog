package com.microblog.search.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.search.service.BlogSearchService;
import com.microblog.search.service.UserSearchService;
import com.microblog.search.service.dto.SearchBlogDto;
import com.microblog.search.web.contants.SearchType;
import com.microblog.user.dao.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "/search",description = "搜索 controller")
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private BlogSearchService blogSearchService;

    @Autowired
    private UserSearchService userSearchService;




    @ApiOperation(value = "/query",httpMethod = "POST",notes="搜索")
    @PrintUrlAnno
    @RequestMapping("/query")
    public BaseResult query(@RequestParam("type") String type,@RequestParam("queryString") String queryString){



        log.info("type = {},queryString={}",type,queryString);
        if(StringUtils.isEmpty(type) || StringUtils.isEmpty(queryString)){

            return new WebResult(WebResult.RESULT_FAIL,"搜索失败，搜索字段不能为空！");

        }

        if(SearchType.USER.equals(type)){
            List<User> result = userSearchService.queryUser(queryString);
            return new WebResult(WebResult.RESULT_SUCCESS,"搜索用户信息成功",result);
        }
        else if(SearchType.BLOG.equals(type)){

            List<SearchBlogDto> result = blogSearchService.queryUser(queryString);
            return new WebResult(WebResult.RESULT_SUCCESS,"搜索博客信息成功",result);
        }

        return new WebResult(WebResult.RESULT_FAIL,"搜索失败！");

    }
}
