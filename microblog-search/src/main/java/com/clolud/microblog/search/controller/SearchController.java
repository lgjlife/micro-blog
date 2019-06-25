package com.clolud.microblog.search.controller;


import com.clolud.microblog.search.constant.SearchType;
import com.clolud.microblog.search.service.BlogSearchService;
import com.clolud.microblog.search.service.UserSearchService;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 *功能描述 
 * @author lgj
 * @Description  搜索 controller
 * @date 3/30/19
*/
//@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


 //   @Autowired
    UserSearchService userSearchService;

  //  @Autowired
    BlogSearchService blogSearchService;


    @PrintUrlAnno
    @RequestMapping("/")
    public BaseResult  search(@RequestBody Map<String,Object>  requestMap) {

      //  elasticsearchTemplate.createIndex()
        BaseResult baseResult = null;

        String type = (String)requestMap.get("type");
        int page = (int)requestMap.get("page");
        int count = (int)requestMap.get("count");
        String query = (String)requestMap.get("query");

        

        if(SearchType.SEARCH_TYPE_BLOG.equals(type)){
            blogSearchService.search(page,count,query);
        }
        else if(SearchType.SEARCH_TYPE_USER.equals(type)){
            userSearchService.search(page,count,query);
        }


        return  baseResult;

    }
}
