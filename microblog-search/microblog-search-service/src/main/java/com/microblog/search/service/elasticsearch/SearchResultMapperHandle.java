package com.microblog.search.service.elasticsearch;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 *功能描述
 * @author lgj
 * @Description  处理查询结果
 * @date 5/19/19
*/
@Component
@Slf4j
public class SearchResultMapperHandle implements SearchResultMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        SearchHits searchHits = searchResponse.getHits();

        List<T> results=null;
        if(searchHits.getHits().length <= 0){
            return  null;
        }
        results = new ArrayList<>();
        for (SearchHit searchHit:searchHits){

            try{
                log.info("searchHit = " + searchHit.getSourceAsString());
                T obj = objectMapper.readValue(searchHit.getSourceAsString(),aClass);

                Field[] fields =   aClass.getDeclaredFields();
                Map<String, HighlightField> highlightFieldMap =  searchHit.getHighlightFields();

                boolean flag = false;
                for(Field field:fields){
                    if(highlightFieldMap.containsKey(field.getName())){
                        if(field.getType() != String.class){
                            break;
                        }
                        field.setAccessible(true);
                        String highlightFieldValue =  Arrays.toString(highlightFieldMap.get(field.getName()).getFragments());
                        field.set(obj,highlightFieldValue);

                        flag = true;
                    }

                }
                if(flag){
                    results.add(obj);
                }

            }
            catch(Exception ex){
                log.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        //log.info("size = " + results.size() + "results:"+results);
        AggregatedPage page = new AggregatedPageImpl<T>(results);
        return page;
    }

}
