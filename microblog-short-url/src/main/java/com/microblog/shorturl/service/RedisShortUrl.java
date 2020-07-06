package com.microblog.shorturl.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 *功能描述 
 * @author lgj
 * @Description  使用redis lua脚本实现短网址实现类
 * @date 9/4/19
*/

@Slf4j
@Service
public class RedisShortUrl implements ShortUrl {

    private static final int radix = 62;

    private final String prex = "{ShortUrl}-";

    @Autowired
    private RedisTemplate  redisTemplate;

    private DefaultRedisScript<List> redisScript;

    private DefaultRedisScript<List> redisScript1;

    private String[] scriptPaths = {"luascript/originToShort.lua","luascript/shortToorigin.lua"};
    private Map<String , DefaultRedisScript<List>> redisScriptMap = new HashMap();

    @PostConstruct
    public void init(){

        for(int i = 0; i< scriptPaths.length; i++){

            redisScript = new DefaultRedisScript<List>();
            redisScript.setResultType(List.class);
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptPaths[i])));
            redisScriptMap.put(scriptPaths[i],redisScript);

        }

    }

    @Override
    public String originToShort(String originUrl) {

        List keys = new ArrayList<String>();
        keys.add(prex+originUrl);
        keys.add(prex+"maxId");
        keys.add(prex);

        List<Long> result =  (List)redisTemplate.execute(redisScriptMap.get(scriptPaths[0]),keys,new Object[]{});

        String shortUrl =  RadixUtil.numberToString(((Number)result.get(0)).longValue(),radix);
        log.info("shortUrl = " + shortUrl);
        return shortUrl;
    }

    @Override
    public String shortToOrigin(String shortUrl) {

        long id = RadixUtil.stringToNumber(shortUrl,radix);

        List keys = new ArrayList<String>();
        keys.add(id);


        List<String> result =  (List)redisTemplate.execute(redisScriptMap.get(scriptPaths[1]),keys,new Object[]{});

        String originUrl = decoderUrl(result.get(0));
        log.info("originUrl = " + originUrl);

        return originUrl;
    }

    private String decoderUrl(String url){

        return url.substring(prex.length(),url.length());
    }


}
