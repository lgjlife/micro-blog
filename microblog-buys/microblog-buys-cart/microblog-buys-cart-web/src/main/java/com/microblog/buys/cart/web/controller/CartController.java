package com.microblog.buys.cart.web.controller;

import com.microblog.buys.cart.service.CartService;
import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.microblog.util.response.ServerResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PrintUrlAnno
    @RequestMapping("/query")
    public ServerResponseDto query(){

        ServerResponseDto responseDto = cartService.selectAll();
        log.info("responseDto = " + responseDto);
        return responseDto;

    }


    @PrintUrlAnno
    @RequestMapping("/delete")
    public ServerResponseDto delete(){

        ServerResponseDto responseDto = cartService.delete();
        log.info("responseDto = " + responseDto);
        return responseDto;

    }

}
