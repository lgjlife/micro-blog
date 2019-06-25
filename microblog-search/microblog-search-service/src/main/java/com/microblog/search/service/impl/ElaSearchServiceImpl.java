package com.microblog.search.service.impl;


import com.microblog.search.service.elasticsearch.SearchResultMapperHandle;
import com.microblog.search.service.pojo.SearchDemo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Slf4j
@Service
public class ElaSearchServiceImpl implements ElaSearchService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private SearchResultMapperHandle searchResultMapperHandle;


    /**
     *功能描述
     * @author lgj
     * @Description 创建索引
     * @date 5/19/19
     * @param:
     * @return:
     *
    */
    @Override
    public void createIndex(SearchDemo demo) {
        log.info("demo = " + demo);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(String.valueOf(demo.getId()))
                .withType(demo.getType())
                .withIndexName(demo.getIndex())
                .withObject(demo)
                .build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Override
    public void deleteIndex(SearchDemo demo) {

        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setType(demo.getType());
        deleteQuery.setIndex(demo.getIndex());
        elasticsearchTemplate.delete(deleteQuery);
    }

    @Override
    public void updateIndex(SearchDemo demo) {

        HashMap<String,String> map = new HashMap<>();
        map.put("name","ssssss");
        UpdateRequest updateRequest = new UpdateRequest();

        updateRequest.doc(map);

        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withId(String.valueOf(demo.getId()))
                .withType(demo.getType())
                .withIndexName(demo.getIndex())
                .withUpdateRequest(updateRequest)
                .withClass(SearchDemo.class)
                .build();

        elasticsearchTemplate.update(updateQuery);
    }

    /**
     *功能描述
     * @author lgj
     * @Description 搜索
     * @date 5/19/19
    */
    @Override
    public <T> List<T> search(int page, int count, String query,Class<T> clazz){

        String preTag = "<font color=‘#dd4b39‘>";//google的色值
        String postTag = "</font>";


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("content").preTags(preTag).postTags(postTag);

        // 分页参数
        Pageable pageable =  PageRequest.of(page, count);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(query);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


               /* .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
                        ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分*/

        HighlightBuilder.Field field  = new HighlightBuilder.Field("content")
                .preTags(preTag).postTags(postTag);
        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(functionScoreQueryBuilder)//.build();
                .withHighlightBuilder(new HighlightBuilder()
                        .preTags(preTag)
                        .postTags(postTag))
                .withHighlightFields(field)
                .withFields("content")
                .withQuery(builder)
                .build();

        SearchQuery searchQuery1 = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(query))
                .withPageable(pageable)
                .withHighlightFields(field)
                .withFields("*")
                .withTypes("book-type")
                .build();

        //List<T> result = elasticsearchTemplate.queryForList(searchQuery1,clazz);
        List<T> result = null;

        AggregatedPage<T> resultPage  = elasticsearchTemplate.queryForPage(searchQuery1,clazz,searchResultMapperHandle);
        result= resultPage.getContent();
        return  result;
    }


}




