package com.clolud.microblog.search.service;

import com.clolud.microblog.search.entity.Blog;

import java.util.List;

public interface BlogSearchService {

    List<Blog> search(int page, int count, String query);
}
