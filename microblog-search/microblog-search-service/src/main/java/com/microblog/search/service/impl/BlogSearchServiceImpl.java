package com.microblog.search.service.impl;


import com.microblog.blog.dao.model.Blog;
import com.microblog.search.service.BlogSearchService;
import com.microblog.search.service.dto.SearchBlogDto;
import com.microblog.search.service.elasticsearch.ElasticsearchHandler;
import com.microblog.search.service.elasticsearch.SearchConfig;
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

    @Override
    public List<SearchBlogDto> queryUser(String queryString) {


        String[]  types = {"blog"};

        SearchConfig searchConfig =  SearchConfig.builder()
                .queryString(queryString)
                .highlightField("content")
                .types(types)
                .clazz(Blog.class).build();

        log.info("SearchConfig = " + searchConfig);
        List<Blog> blogs = elasticsearchHandler.query().search(searchConfig);
        List<SearchBlogDto> searchBlogDtos = new ArrayList<>(blogs.size());

        for(int i = 0; i< blogs.size(); i++){
            Blog blog = blogs.get(i);
            SearchBlogDto searchBlogDto = SearchBlogDto.builder()
                    .blogId(blog.getBlogId())
                    .userId(blog.getUserId())
                    .content(blog.getContent())
                    .publishTime(blog.getPublishTime().getTime())
                    .build();

            searchBlogDtos.add(searchBlogDto);
        }
        log.info("搜索结果:[{}],users = {}",searchBlogDtos.size(),  searchBlogDtos);

        return searchBlogDtos;
    }
}
