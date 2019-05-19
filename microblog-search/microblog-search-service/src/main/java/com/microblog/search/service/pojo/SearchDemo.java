package com.microblog.search.service.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName="searchdemo-index")
public class SearchDemo {

    private  long id;

    private  String name;


    private String content;

    private String type;

    private String index;


}
