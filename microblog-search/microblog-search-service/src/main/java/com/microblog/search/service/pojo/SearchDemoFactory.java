package com.microblog.search.service.pojo;

import java.util.Random;

public class SearchDemoFactory {

    public static SearchDemo createSearchDemo(){
        SearchDemo demo = new SearchDemo();
        demo.setId(new Random().nextInt(1000));
        demo.setName("demo");
        demo.setContent("嘟嘟网络游戏服务网为全球华人玩家提供担保,租购售游戏币、帐号、装备、点券、代练等服务。手游首充号让利给更多玩家!DD373致力于免手续费的网络游戏交易平台");
        demo.setType("book-type");
        demo.setIndex("searchdemo-index");
        return demo;
    }
}
