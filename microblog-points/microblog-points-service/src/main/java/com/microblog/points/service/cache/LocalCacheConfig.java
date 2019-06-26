package com.microblog.points.service.cache;

import com.microblog.cache.localcache.GuavaLocalCache;
import com.microblog.cache.localcache.LocalCache;
import com.microblog.points.dao.model.Points;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LocalCacheConfig {

    @Bean
    public LocalCache<String, Points> pointCache(){
        LocalCache cache = new GuavaLocalCache<String, Points>();
        ((GuavaLocalCache) cache).setExpireTimeSecond(60*20);
        cache.init(null);
        return cache;
    }

    @Bean
    public LocalCache<String, String> stringCache(){
        LocalCache cache = new GuavaLocalCache<String, String>();
        return cache;
    }
}
