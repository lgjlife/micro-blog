package com.microblog.cache.localcache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;


@Slf4j
public class GuavaLocalCacheTest {

    @Test
    public void set() throws Exception{

        LocalCache<String,String> localCache = new GuavaLocalCache<>();

        ((GuavaLocalCache<String, String>) localCache).setExpireTimeSecond(10);
        localCache.init("null");
        localCache.set("key","value");
        log.info("result  = " + localCache.get("key"));

        Thread.sleep(12000);
        log.info("result  = " + localCache.get("key"));
        log.info("result  = " + localCache.get("key1"));


    }
}