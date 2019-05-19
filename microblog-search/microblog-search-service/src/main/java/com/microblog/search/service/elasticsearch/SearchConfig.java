package com.microblog.search.service.elasticsearch;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SearchConfig {

    /***
     * 起始页，０开始
     */
    private int page = 0;

    /***
     * 每页的数据条数
     */
    private int count = 10;

    /***
     *　查询字符串
     */
    private String queryString;

    /***
     *  查询对应的类
     */
    private Class clazz;

    /***
     * 高亮显示的颜色
     */
    private String highlightColor = "#dd4b39";

    /***
     * 高亮显示的字段
     */
    private String highlightField;

    /***
     * 需要查询的字段,默认查询所有字段
     */
    private String[] feilds = {"*"};

    /***
     * 查询type
     */
    private String[] types;
}
