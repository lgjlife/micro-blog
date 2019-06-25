package com.microblog.search.service.elasticsearch;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Component
public class ElasticsearchQueryHandler {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private SearchResultMapperHandle searchResultMapperHandle;



    /**
     *功能描述
     * @author lgj
     * @Description  查询数据
     * @date 5/19/19
     * @param:
     * @return:
     *
    */
    public <T> List<T> search(SearchConfig searchConfig){

        String preTag = "<font color='" + searchConfig.getHighlightColor()+"'>";
        String postTag = "</font>";


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("content").preTags(preTag).postTags(postTag);

        // 分页参数
        Pageable pageable =  PageRequest.of(searchConfig.getPage(), searchConfig.getCount());
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchConfig.getQueryString());
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);
        HighlightBuilder.Field field  = new HighlightBuilder.Field(searchConfig.getHighlightField())
                .preTags(preTag).postTags(postTag);
        // 分数、分页

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(searchConfig.getQueryString()))
                //分页
                .withPageable(pageable)
                //高亮
                .withHighlightFields(field)
                //查询字段
                .withFields(searchConfig.getFeilds())
                //查询type
                .withTypes(searchConfig.getTypes())


                .build();

        List<T> result = null;

        AggregatedPage<T> resultPage  = elasticsearchTemplate.queryForPage(searchQuery,searchConfig.getClazz(),searchResultMapperHandle);
        result= resultPage.getContent();
        return  result;
    }


}
