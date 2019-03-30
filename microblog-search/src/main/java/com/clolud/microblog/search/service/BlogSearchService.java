package com.clolud.microblog.search.service;

import com.cloud.microblog.blog.dao.model.Blog;

import java.util.List;

public interface BlogSearchService {

    List<Blog> search(int page, int count, String query);
}
