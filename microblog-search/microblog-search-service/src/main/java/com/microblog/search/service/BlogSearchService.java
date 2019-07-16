package com.microblog.search.service;

import com.microblog.blog.dao.dto.BlogInfoDto;

import java.util.List;

public interface BlogSearchService {

    List<BlogInfoDto> queryBlog(String queryString);

}
