package com.microblog.search.service.elasticsearch;


import lombok.Data;
import lombok.ToString;


@Data
/*@Builder*/
@ToString
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

    public static Builder builder(){
        Builder builder = new Builder();
        return builder;
    }


    public static class Builder{

        private int page = 0;

        private int count = 10;

        private String queryString;

        private Class clazz;

        private String highlightColor = "red";

        private String highlightField;

        private String[] feilds = {"*"};

        private String[] types;




        public Builder page(int page){
            this.page = page;
            return this;
        }

        public Builder count(int count){
            this.count = count;
            return this;
        }

        public Builder queryString(String queryString){
            this.queryString = queryString;
            return this;
        }

        public Builder clazz(Class clazz){
            this.clazz = clazz;
            return this;
        }

        public Builder highlightColor(String highlightColor){
            this.highlightColor = highlightColor;
            return this;
        }

        public Builder highlightField(String highlightField){
            this.highlightField = highlightField;
            return this;
        }

        public Builder feilds(String[] feilds){
            this.feilds = feilds;
            return this;
        }

        public Builder types(String[] types){
            this.types = types;
            return this;
        }


        public SearchConfig build(){
            SearchConfig config = new SearchConfig();
            config.setPage(this.page);
            config.setCount(this.count);
            config.setQueryString(this.queryString);
            config.setClazz(this.clazz);
            config.setHighlightColor(this.highlightColor);
            config.setHighlightField(this.highlightField);
            config.setFeilds(this.feilds);
            config.setTypes(this.types);

            return config;
        }
    }
}
