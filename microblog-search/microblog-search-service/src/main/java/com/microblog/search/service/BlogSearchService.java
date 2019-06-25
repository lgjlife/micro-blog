package com.microblog.search.service;

import com.microblog.search.service.dto.SearchBlogDto;

import java.util.List;

public interface BlogSearchService {

    List<SearchBlogDto> queryUser(String queryString);

}
