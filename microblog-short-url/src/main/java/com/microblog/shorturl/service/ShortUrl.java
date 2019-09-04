package com.microblog.shorturl.service;

/**
 *功能描述 
 * @author lgj
 * @Description  短网址接口
 * @date 9/4/19
*/
public interface ShortUrl {

    String originToShort(String originUrl);
    String shortToOrigin(String shortUrl);
}
