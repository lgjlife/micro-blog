package com.microblog.search.service.handle;

import com.microblog.search.service.pojo.SearchDemo;
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


@Component
@Slf4j
public class SearchResultMapperHandle implements SearchResultMapper {

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

        log.info("searchResponse = " + searchResponse);
        log.info("aClass = " + aClass);
        log.info("pageable = " + pageable);

        SearchHits searchHits = searchResponse.getHits();


/***
 * searchResponse =
 *      {"took":25,
 *          "timed_out":false,
 *          "_shards":{"total":5,
 *          "successful":5,
 *          "skipped":0,
 *          "failed":0
 *      },
 *      "_clusters":
 *          {   "total":0,
 *              "successful":0,
 *              "skipped":0},
 *              "hits":
 *                  {   "total":17,
 *                      "max_score":0.08362578,
 *                      "hits":[
 *                          {"_index":"searchdemo-index","_type":"book-type","_id":"79","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":79,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"119","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":119,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"865","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":865,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"100","_version":2,"_score":0.08362578,"_source":{"name":"ssssss","index":"searchdemo-index","id":100,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"702","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":702,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"209","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":209,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"281","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":281,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"392","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":392,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"690","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":690,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}},{"_index":"searchdemo-index","_type":"book-type","_id":"809","_version":1,"_score":0.08362578,"_source":{"name":"demo","index":"searchdemo-index","id":809,"type":"book-type","content":"嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台"},"highlight":{"content":["嘟嘟<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏服务<font color=‘#dd4b39‘>网</font>为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的<font color=‘#dd4b39‘>网</font><font color=‘#dd4b39‘>络</font>游戏交易平台"]}}]}}
 *
 *
 */
        List<T> results = new ArrayList<>();
        for (SearchHit searchHit:searchHits){

            try{
                SearchDemo demo =    SearchDemo.class.newInstance();
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


           // searchHit.

        }
        AggregatedPage page = new AggregatedPageImpl<T>(results);
        return page;
    }

    private  String getFieldSetteName(String fieldName){


        fieldName.toUpperCase();
        return  null;
    }

    public static void main(String args[]){

       try{

           PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name",SearchDemo.class);

           Method method = propertyDescriptor.getWriteMethod();

           System.out.println(method);

       }
       catch(Exception ex){
           log.error(ex.getMessage());
       }
        System.out.println("asdfa".toUpperCase());
    }


}
