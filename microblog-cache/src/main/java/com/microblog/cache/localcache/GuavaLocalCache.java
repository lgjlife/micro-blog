package com.microblog.cache.localcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class GuavaLocalCache<Sring,T> implements LocalCache<Sring,T> {

    private LoadingCache<String, T> cache;

    private long maxSize = 1000;
    private long expireTimeSecond = 60;


    @Override
    public void init(T defaultObject) {
        cache = CacheBuilder
                .newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expireTimeSecond, TimeUnit.SECONDS)
                .build(new CacheLoader<String,T>(){

                    public T load(String s) throws Exception {
                        return null;
                    }
                });
    }

    @Override
    public void set(String key, T object) {
        cache.put(key,object);
    }

    @Override
    public T get(String key){
        try{
            return   cache.get(key);
        }
        catch(Exception ex){
            log.warn("Cache Key[{}] is not exist! ex = [{}]",key,ex.getMessage());
        }
        return null;
    }

    @Override
    public void remove(String key) {
        try{
            cache.invalidate(cache.get(key));
        }
        catch(Exception ex){
            log.warn("Cache Key[{}] is not exist! ex = [{}]",key,ex.getMessage());
        }

    }

    @Override
    public void removeaAll() {
        cache.invalidateAll();
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public void setExpireTimeSecond(long expireTimeSecond) {
        this.expireTimeSecond = expireTimeSecond;
    }
}
