package com.microblog.search.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.search.service.impl.ElaSearchService;
import com.microblog.search.service.pojo.SearchDemo;
import com.microblog.search.service.pojo.SearchDemoFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ela")
public class ElaSearchController {

    @Autowired
    private ElaSearchService elaSearchService;


    @PrintUrlAnno
    @RequestMapping("/create/index")
    public SearchDemo createIndex(){
        SearchDemo  demo = SearchDemoFactory.createSearchDemo();

        elaSearchService.createIndex(demo);

        return demo;
    }

    @PrintUrlAnno
    @RequestMapping("/delete/index")
    public SearchDemo deleteIndex(){
        SearchDemo  demo = SearchDemoFactory.createSearchDemo();

        elaSearchService.deleteIndex(demo);

        return demo;
    }

    @PrintUrlAnno
    @RequestMapping("/update/index")
    public SearchDemo updateIndex(){
        SearchDemo  demo = SearchDemoFactory.createSearchDemo();

        elaSearchService.updateIndex(demo);

        return demo;
    }

    @PrintUrlAnno
    @RequestMapping("/query/index/{data}")
    public List searchIndex(@PathVariable("data") String data){
        SearchDemo  demo = SearchDemoFactory.createSearchDemo();

        log.info("搜索data:" + data);

        return elaSearchService.search(0,10,data,SearchDemo.class);

    }
}
