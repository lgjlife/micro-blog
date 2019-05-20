package com.microblog.search.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.search.service.BlogSearchService;
import com.microblog.search.service.UserSearchService;
import com.microblog.search.service.dto.SearchBlogDto;
import com.microblog.search.service.dto.SearchUserDto;
import com.microblog.search.service.result.SearchReturnCode;
import com.microblog.search.web.contants.SearchType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private BlogSearchService blogSearchService;

    @Autowired
    private UserSearchService userSearchService;




    @PrintUrlAnno
    @RequestMapping("/query")
    public BaseResult query(@RequestBody Map<String,String> requestMap){

        String type = requestMap.get("type");
        String queryString = requestMap.get("queryString");

        log.info("requestMap = " + requestMap);
        if(StringUtils.isEmpty(type) || StringUtils.isEmpty(queryString)){

            return new WebResult(SearchReturnCode.SEARCH_NULL_PARAM);

        }

        if(SearchType.USER.equals(type)){
            List<SearchUserDto> result = userSearchService.queryUser(queryString);
            return new WebResult(SearchReturnCode.SEARCH_SUCCESS,result);
        }
        else if(SearchType.BLOG.equals(type)){

            List<SearchBlogDto> result = blogSearchService.queryUser(queryString);
            return new WebResult(SearchReturnCode.SEARCH_SUCCESS);
        }

        return new WebResult(SearchReturnCode.SEARCH_FAIL);

    }
}
