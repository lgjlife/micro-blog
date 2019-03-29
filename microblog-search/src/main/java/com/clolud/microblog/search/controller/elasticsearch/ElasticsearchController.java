package com.clolud.microblog.search.controller.elasticsearch;


import com.clolud.microblog.search.pojo.Book;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/ela")
public class ElasticsearchController {

    @Autowired
    TransportClient elasticsearchClient;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;



    @PrintUrlAnno
    @GetMapping("/add")
    public GetResponse add(){

        log.info("/ela/add");

        Book book  = new Book("Redis 入门","梁国","1000");

        try{
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", book.getTitle())
                    .field("author", book.getAuthor())
                    .field("word_count", book.getWordCount())
                    .endObject();


            //创建文档
            IndexResponse indexResponse = elasticsearchClient
                    .prepareIndex("book","novel")
                    .setSource(content)
                    .get();

            log.info(indexResponse.toString());

            GetResponse response =  elasticsearchClient
                    .prepareGet("book","novel",indexResponse.getId())
                    .get();

            log.info(response.toString());


            SearchResponse  searchResponse =  elasticsearchClient.prepareSearch("book")

                    .setTypes("novel")
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setFrom(0)
                    .setSize(5)
                    .get();

            log.info("searchResponse = " + searchResponse.toString());

            return  response;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return  null;

    }

    @PrintUrlAnno
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request){
        log.info("/ela/delete");
    }

    @PrintUrlAnno
    @RequestMapping("/query")
    public SearchResponse query(@RequestParam("query") String query){

        log.debug("query {}",query);
        SearchResponse  searchResponse =  elasticsearchClient.prepareSearch(query)
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFrom(0)
                .setSize(5)
                .get();
        return searchResponse;


    }


}
