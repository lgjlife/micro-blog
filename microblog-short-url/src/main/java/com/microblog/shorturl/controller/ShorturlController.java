package com.microblog.shorturl.controller;


import com.microblog.shorturl.service.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shorturl")
public class ShorturlController {


    @Autowired
    private ShortUrl shortUrlservice;



    @GetMapping("/short")
    public String getOriginUrl(String originUrl){
        return shortUrlservice.originToShort(originUrl);
    }

    @GetMapping("/origin")
    public String getShortUrl(String shortUrl){

        return shortUrlservice.shortToOrigin(shortUrl);
    }

}
