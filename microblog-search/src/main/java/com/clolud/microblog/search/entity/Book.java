package com.clolud.microblog.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


//@Builder
@Data
@Document(indexName = "product", type = "book")
public class Book {
    @Id
    String id;
    String name;
    String message;
    String type;

    public  Book(){

    }

    public Book(String id, String name, String message, String type) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.type = type;
    }
}

