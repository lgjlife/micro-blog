package com.clolud.microblog.search.util;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.AbstractResultMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.LinkedList;


//@Component
public class ExtResultMapper extends AbstractResultMapper {



    public ExtResultMapper(EntityMapper entityMapper) {
        super(entityMapper);
    }

    @Override
    public <T> T mapResult(GetResponse getResponse, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> LinkedList<T> mapResults(MultiGetResponse multiGetResponse, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        return null;
    }
}