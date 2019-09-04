package com.microblog.shorturl.service;


import com.microblog.shorturl.dao.mapper.UrlMapper;
import com.microblog.shorturl.dao.model.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *功能描述 
 * @author lgj
 * @Description  默认的短网址实现类
 * @date 9/4/19
*/

@Slf4j
//@Service
public class DefaultShortUrl implements ShortUrl {

    private static final int radix = 62;

    @Autowired
    private UrlMapper urlMapper;


    @Override
    public String originToShort(String originUrl) {

        log.info("originUrl = " + originUrl);
        long id = 0;
        Url url = urlMapper.selectByUrl(originUrl);

        log.info("url = " + url);
        if(url == null){
            url = new Url();
            url.setUrl(originUrl);
            urlMapper.insert(url);

        }
        id = url.getId();


        String shortUrl =  RadixUtil.numberToString(id,radix);
        log.info("shortUrl = " + shortUrl);
        return shortUrl;
    }

    @Override
    public String shortToOrigin(String shortUrl) {

        long id = RadixUtil.stringToNumber(shortUrl,radix);
        Url url = urlMapper.selectByPrimaryKey(id);
        return null==url?null:url.getUrl();
    }


}
