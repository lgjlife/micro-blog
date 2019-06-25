package com.microblog.search.service.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *功能描述
 * @author lgj
 * @Description  创建索引处理
 * @date 5/19/19
*/
@Component
public class ElasticsearchCreateHandler {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;



    /**
     *功能描述 
     * @author lgj
     * @Description  
     * @date 5/19/19
     * @param:  
     * @return:  
     *
    */
    public void createIndex(String id,String type ,String indexName,Object object) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withType(type)
                .withIndexName(indexName)
                .withObject(object)
                .build();
        elasticsearchTemplate.index(indexQuery);
    }

    /**
     *功能描述
     * @author lgj
     * @Description  批量插入
     * @date 5/19/19
     * @param:
     * @return:
     *
    */
    public void bulkIndex(List<String> ids, String type , String indexName, List<Object> objects) {

        List<IndexQuery > indexQuerys = new ArrayList<>();

        for(int i = 0; i< objects.size(); i++){
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(ids.get(i))
                    .withType(type)
                    .withIndexName(indexName)
                    .withObject(objects.get(i))
                    .build();
            indexQuerys.add(indexQuery);
        }

        elasticsearchTemplate.bulkIndex(indexQuerys);
    }



}
