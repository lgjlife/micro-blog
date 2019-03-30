package com.clolud.microblog.search.service.impl;

import com.clolud.microblog.search.dao.BlogRepository;
import com.clolud.microblog.search.service.BlogSearchService;
import com.cloud.microblog.blog.dao.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class BlogSearchServiceImpl implements BlogSearchService {

    @Autowired
    BlogRepository   blogRepository;




    @Override
    public List<Blog> search(int page, int count, String query) {

        String preTag = "<font color=‘#dd4b39‘>";//google的色值
        String postTag = "</font>";


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("message").preTags(preTag).postTags(postTag);

        // 分页参数
        Pageable pageable =  PageRequest.of(page, count);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(query);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


               /* .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
                        ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分*/

        HighlightBuilder.Field field  = new HighlightBuilder.Field("message");
        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(functionScoreQueryBuilder)//.build();
                .withHighlightBuilder(new HighlightBuilder()
                        .preTags(preTag)
                        .postTags(postTag))
                .withHighlightFields(field)
                .build();

        Page<Blog> searchPageResults = blogRepository.search(searchQuery);

        return searchPageResults.getContent();
    }
}
