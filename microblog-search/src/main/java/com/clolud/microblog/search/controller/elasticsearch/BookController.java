package com.clolud.microblog.search.controller.elasticsearch;


import com.clolud.microblog.search.dao.BookDao;
import com.clolud.microblog.search.entity.Book;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookDao bookDao;


    @PrintUrlAnno
    @GetMapping("/get/{id}")
    public Book getBookById(@PathVariable String id) {
        log.debug("id = " + id);
        Optional<Book> bookOptional;
        bookOptional = bookDao.findById(id);
        return bookOptional.get();

    }

    @PrintUrlAnno
    @GetMapping("/select/{q}")
    public List<Book> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<Book> searchResult = bookDao.search(builder);

        Iterator<Book> iterator = searchResult.iterator();
        List<Book> list = new ArrayList<Book>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @PrintUrlAnno
    @GetMapping("/{page}/{size}/{q}")
    public List<Book> searchCity(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {

        String preTag = "<font color=‘#dd4b39‘>";//google的色值
        String postTag = "</font>";


        HighlightBuilder  highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("message").preTags(preTag).postTags(postTag);

        // 分页参数
        Pageable pageable =  PageRequest.of(page, size);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
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

        Page<Book> searchPageResults = bookDao.search(searchQuery);

        return searchPageResults.getContent();

    }

    /**
     * 4、增
     * @param
     * @return
     */
    @PrintUrlAnno
    @PostMapping("/insert")
    public Book insertBook() {

        long radom = new Random().nextInt(100);
        Book book = new Book(String.valueOf(radom),"中国梦想"+ radom,
                "Elasticsearch,提供",
                "book122");
        bookDao.save(book);
        return book;
    }

    /**
     * 5、删 id
     * @param id
     * @return
     */
    @PrintUrlAnno
    @DeleteMapping("/delete/{id}")
    public Book deleteBook(@PathVariable String id) {
        Book book = bookDao.findById(id).get();
        bookDao.delete(book);
        return book;
    }

    /**
     * 6、改
     * @param book
     * @return
     */
    @PrintUrlAnno
    @PutMapping("/update")
    public Book updateBook(Book book) {
        bookDao.save(book);
        return book;
    }


}
