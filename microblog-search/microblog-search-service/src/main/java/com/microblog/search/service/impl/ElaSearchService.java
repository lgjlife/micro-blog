package com.microblog.search.service.impl;

import com.microblog.search.service.pojo.SearchDemo;

import java.util.List;

public interface ElaSearchService {

    void createIndex(SearchDemo demo);
    void deleteIndex(SearchDemo demo);
    void updateIndex(SearchDemo demo);

    <T> List<T> search(int page, int count, String query,Class<T> clazz);

}
