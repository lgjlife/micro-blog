package com.clolud.microblog.search.controller.elasticsearch;


import com.clolud.microblog.search.dao.BookDao;
import com.clolud.microblog.search.entity.Book;
import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


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



    /**
     * 4、增
     * @param book
     * @return
     */
    @PrintUrlAnno
    @PostMapping("/insert")
    public Book insertBook() {
        Book book = new Book("2","中国梦想",
                "Elasticsearch,提供",
                "book");
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
