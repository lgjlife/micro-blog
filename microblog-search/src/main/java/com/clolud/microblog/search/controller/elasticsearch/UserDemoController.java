package com.clolud.microblog.search.controller.elasticsearch;


import com.clolud.microblog.search.dao.Book;
import com.clolud.microblog.search.entity.Blog;
import com.clolud.microblog.search.entity.Blog1;
import com.clolud.microblog.search.entity.UserDemo;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.user.dao.model.Location;
import com.cloud.microblog.user.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;


@Slf4j
@RequestMapping("/user/demo")
@RestController
public class UserDemoController {


    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;



    @PrintUrlAnno
    @GetMapping("/{page}/{size}/{q}")
    public  List<UserDemo> query(@PathVariable Integer page,
                                 @PathVariable Integer size,
                                 @PathVariable String q,
                                 @PageableDefault Pageable myPage) {

        System.out.println("query 11111  qstr=" + q);
        log.info("query ... ");

        log.info("page = {},size ={},q={}",page,size,q);


        String preTag = "<font color=‘#dd4b39‘>";
        String postTag = "</font>";


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("nick_name").preTags(preTag).postTags(postTag);

        // 分页参数
        Pageable pageable =  PageRequest.of(page, size);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


               /* .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
                        ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分*/

        HighlightBuilder.Field field  = new HighlightBuilder.Field("nick_name");

        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(queryStringQuery(q))
                /*.withIndices("user")
                .withTypes("demo")*/
              //  .withFields("userNumber","nickName","loginPassword","salt")
             /*   .withHighlightBuilder(new HighlightBuilder()
                        .preTags(preTag)
                        .postTags(postTag))
                .withHighlightFields(field)*/
                .build();

        List<UserDemo>  userDemos = elasticsearchTemplate.queryForList(searchQuery,UserDemo.class);

        log.info("userDemos = " + userDemos);
        return  userDemos;
    }

    @PrintUrlAnno
    @GetMapping("/book/{q}")
    public  List<Book> queryBook(@PathVariable String q,
                             @PageableDefault Pageable myPage) {

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


        SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(functionScoreQueryBuilder)
                .withIndices("product")
                .withTypes("book")
                /*   .withHighlightBuilder(new HighlightBuilder()
                           .preTags(preTag)
                           .postTags(postTag))
                   .withHighlightFields(field)*/
                .build();

        List<Book>  books = elasticsearchTemplate.queryForList(searchQuery1, Book.class);

        log.info("books len  ={} , {} ",books.size(), books);
        return books;
    }

    @PrintUrlAnno
    @GetMapping("/user/{q}")
    public  List<User> queryUser(@PathVariable String q,
                                 @PageableDefault Pageable myPage) {

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


        SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(functionScoreQueryBuilder)
                .withIndices("index_user")
                .withTypes("user")
                /*   .withHighlightBuilder(new HighlightBuilder()
                           .preTags(preTag)
                           .postTags(postTag))
                   .withHighlightFields(field)*/
                .build();

        List<User>  users = elasticsearchTemplate.queryForList(searchQuery1, User.class);

        log.info("userDemos len  ={} , {} ",users.size(), users);
        return users;
    }

    @PrintUrlAnno
    @GetMapping("/location/{q}")
    public  List<Location> queryLocation(@PathVariable String q,
                                     @PageableDefault Pageable myPage) {

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


        SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(functionScoreQueryBuilder)
                .withIndices("index_location")
                .withTypes("location")
                /*   .withHighlightBuilder(new HighlightBuilder()
                           .preTags(preTag)
                           .postTags(postTag))
                   .withHighlightFields(field)*/
                .build();

        List<Location>  locations = elasticsearchTemplate.queryForList(searchQuery1, Location.class);

        log.info("Location len  ={} , {} ",locations.size(), locations);
        return locations;
    }

    @PrintUrlAnno
    @GetMapping("/blog/{q}")
    public  List<Blog> queryBlog(@PathVariable String q,
                                 @PageableDefault Pageable myPage) {

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


        SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(functionScoreQueryBuilder)
               // .withIndices("index_blog")
               // .withTypes("blog")
                /*   .withHighlightBuilder(new HighlightBuilder()
                           .preTags(preTag)
                           .postTags(postTag))
                   .withHighlightFields(field)*/
                .build();

        List<Blog>  blogs = elasticsearchTemplate.queryForList(searchQuery1, Blog.class);

        log.info("Location len  ={} , {} ",blogs.size(), blogs);
        return blogs;
    }

    @PrintUrlAnno
    @GetMapping("/blog1/{q}")
    public  List<Blog1> queryBlog1(@PathVariable String q,
                                   @PageableDefault Pageable myPage) {

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);


        SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(functionScoreQueryBuilder)
                .withIndices("index_blog1")
                .withTypes("blog1")
                /*   .withHighlightBuilder(new HighlightBuilder()
                           .preTags(preTag)
                           .postTags(postTag))
                   .withHighlightFields(field)*/
                .build();

        List<Blog1>  blogs = elasticsearchTemplate.queryForList(searchQuery1, Blog1.class);

        log.info("Location len  ={} , {} ",blogs.size(), blogs);
        return blogs;
    }



}
