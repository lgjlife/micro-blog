package com.clolud.microblog.search.controller.elasticsearch;


import com.clolud.microblog.search.dao.Book;
import com.clolud.microblog.search.entity.Blog;
import com.clolud.microblog.search.entity.UserDemo;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.user.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        String preTag = "<font color=‘#dd4b39‘>";
        String postTag = "</font>";

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);




        SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withPageable(myPage)
                .withQuery(functionScoreQueryBuilder)
                .withIndices("index_user")
                .withTypes("user")
                .withHighlightFields(
                        new HighlightBuilder.Field("nickName").preTags("<span style='color:red'>").postTags("</span>"),
                        new HighlightBuilder.Field("email").preTags("<span style='color:red'>").postTags("</span>"))

                .build();



        Page<User> users = elasticsearchTemplate.queryForPage(searchQuery1, User.class,new SearchResultMapper(){
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                List<T> resluts = new ArrayList<T>();

                for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                    Map<String, Object> smap = searchHit.getSourceAsMap();
                    Map<String, HighlightField> hmap = searchHit.getHighlightFields();
                    T user =  (T)create(smap,hmap);
                    resluts.add(user);
                }

                AggregatedPage<T> result=new AggregatedPageImpl<T>(resluts,pageable,searchResponse.getHits().getTotalHits());
                return result;
            }
        });
        return users.getContent();
    }

    private User create(Map<String, Object> smap ,  Map<String, HighlightField> hmaps ){

        User user =  new User();
        hmaps.forEach((k,v)->{
            smap.put(v.getName(),v.getFragments());
        });
        try{
            log.info("smap = " + smap);

            DateConverter converter = new DateConverter();
            converter.setPattern(new String("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            //SimpleDateFormat

            ConvertUtils.register(new Converter(){
                @Override
                public <T> T convert(Class<T> aClass, Object o) {

                    String dateStr = (String)o;

                    log.info("dateStr = " + dateStr);
                   // dateStr =  "2019-02-25 12:01:02.000Z";  //"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
                    SimpleDateFormat spdt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try {
                        T date = (T)spdt.parse(dateStr);

                        log.info("date  = " + date.toString());
                        return date;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return null;

                }
            },Date.class);
            BeanUtils.populate(user,smap);

            log.info("user = " + user);


        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }


        return  user;
    }

    public static void main(String args[]){
        SimpleDateFormat spdt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        System.out.println(spdt.format(new Date()));


        try{
            String dateStr =  "2019-02-25T12:01:02.000Z";  //"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            System.out.println(sdf.parse(dateStr).toString());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }



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
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        String result = elasticsearchTemplate.delete("index_user","user",id);
        log.debug("result = " + result);
        log.info("result = " + result);
    }

}


