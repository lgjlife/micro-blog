package com.clolud.microblog.search.dao;

import com.clolud.microblog.search.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookDao extends ElasticsearchRepository<Book,String> {

}
