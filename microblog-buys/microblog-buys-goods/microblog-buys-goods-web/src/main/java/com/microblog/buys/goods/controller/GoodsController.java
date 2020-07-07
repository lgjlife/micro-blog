package com.microblog.buys.goods.controller;


import com.microblog.buys.goods.service.GoodsService;
import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.microblog.util.response.ServerResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PrintUrlAnno
    @RequestMapping("/query")
    public ServerResponseDto query(){

        ServerResponseDto responseDto = goodsService.selectAll();
        log.info("responseDto = " + responseDto);
        return responseDto;

    }

    @PrintUrlAnno
    @RequestMapping("/delete")
    public ServerResponseDto delete(){

        ServerResponseDto responseDto = goodsService.delete();
        log.info("responseDto = " + responseDto);
        return responseDto;

    }

}
