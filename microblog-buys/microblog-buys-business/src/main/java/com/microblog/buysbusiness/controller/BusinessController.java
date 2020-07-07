package com.microblog.buysbusiness.controller;


import com.microblog.buysbusiness.feign.cart.CartFeignService;
import com.microblog.buysbusiness.feign.goods.GoodsFeignService;
import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.microblog.util.response.ServerResponseDto;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/business")
@RestController
public class BusinessController {

    @Autowired
    private CartFeignService cartFeignService;

    @Autowired
    private GoodsFeignService goodsFeignService;



    @PrintUrlAnno(layer = "Controller",description="购买操作")
    @RequestMapping("/buy")
    public String buy(){


        return "1234";

    }

    @GlobalTransactional(timeoutMills = 300000, name = "business-delete")
    @PrintUrlAnno(layer = "Controller",description="购买操作")
    @RequestMapping("/delete")
    public ServerResponseDto delete(){
        log.info("全局事务开始");
        ServerResponseDto cartResponse = cartFeignService.delete();
        ServerResponseDto goodsResponse = goodsFeignService.delete();

        log.info("cartResponse = " + cartResponse);
        log.info("goodsResponse = " + goodsResponse);
        log.info("sql操作完成");

        try{

           // Thread.sleep(10*1000);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }


        if(true){
            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
        }

        return new ServerResponseDto(2,"抛出异常");

    }


    @PrintUrlAnno(layer = "Controller",description="查询")
    @RequestMapping("/query")
    public List<ServerResponseDto> query(){
        ServerResponseDto cartResponse = cartFeignService.query();
        ServerResponseDto goodsResponse = goodsFeignService.query();


        List<ServerResponseDto> result = new ArrayList<>();
        result.add(cartResponse);
        result.add(goodsResponse);

        log.info("cartResponse = " + cartResponse);
        log.info("goodsResponse = " + goodsResponse);
        return result;
    }
}
