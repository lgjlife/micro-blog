package com.microblog.search.service.elasticsearch;

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

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        SearchHits searchHits = searchResponse.getHits();

        List<T> results=null;
        if(searchHits.getHits().length <= 0){
            return  null;
        }
        for (SearchHit searchHit:searchHits){

            results = new ArrayList<>();
            try{

                Map<String, Object> resultMap = searchHit.getSourceAsMap();

                T instance =  aClass.newInstance();
                Field[] fields =   aClass.getDeclaredFields();
                Map<String, HighlightField> highlightFieldMap =  searchHit.getHighlightFields();

                for(Field field:fields){
                    field.setAccessible(true);

                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),aClass);
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    HighlightField highlightField = null ;
                    if(( highlightField=highlightFieldMap.get(field.getName())) != null){
                        //field　高亮字段
                        setMethod.invoke(instance,highlightField.getFragments()[0].toString());
                    }
                    else {
                        //field　不是高亮字段
                        setMethod.invoke(instance,resultMap.get(field.getName()));
                    }

                }

                results.add(instance);
            }
            catch(Exception ex){
                log.error(ex.getMessage());
            }
        }
        AggregatedPage page = new AggregatedPageImpl<T>(results);
        return page;
    }
}
