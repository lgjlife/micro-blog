package com.clolud.microblog.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


//@Builder
@Data
@Document(indexName = "product", type = "book")
public class Book {
   //
   // String id;
    @Id
    String ida;
    String name;
    @Field
    String message;
    String type;

    public  Book(){

    }

    public Book(String id, String name, String message, String type) {
        this.ida = id;
        this.name = name;
        this.message = message;
        this.type = type;
    }
}

