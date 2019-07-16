package com.microblog.search.service.impl;


import com.microblog.blog.dao.dto.BlogInfoDto;
import com.microblog.blog.dao.model.Blog;
import com.microblog.search.service.BlogSearchService;
import com.microblog.search.service.elasticsearch.ElasticsearchHandler;
import com.microblog.search.service.elasticsearch.SearchConfig;
import com.microblog.search.service.feign.blog.BlogFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlogSearchServiceImpl implements BlogSearchService {

    @Autowired
    private ElasticsearchHandler elasticsearchHandler;

    @Autowired
    private BlogFeignService blogFeignService;


    @Override
    public List<BlogInfoDto> queryBlog(String queryString) {


        String[]  types = {"blog"};

        SearchConfig searchConfig =  SearchConfig.builder()
                .queryString(queryString)
                .highlightField("content")
                .types(types)
                .clazz(Blog.class).build();

        log.info("SearchConfig = " + searchConfig);
        List<Blog> searchBlogs = elasticsearchHandler.query().search(searchConfig);

        if((searchBlogs == null ) || (searchBlogs.isEmpty())){
            return new ArrayList<>();
        }
        
        log.info("搜索结果:"+searchBlogs);
        List<BlogInfoDto> blogInfoDtos = new ArrayList<>();
        for(Blog blog:searchBlogs){
            log.info("查询参数:blog-id:{},user-id:{}",blog.getBlogId(),blog.getUserId());
            BlogInfoDto blogInfoDto =  blogFeignService.queryBlogfo(blog.getBlogId(),blog.getUserId());
            log.info("blogInfoDto = " + blogInfoDto);
            if(blogInfoDtos!=null){

                blogInfoDtos.add(blogInfoDto);
            }
        }

        return blogInfoDtos;
    }
}
