package com.microblog.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
@SpringBootApplication
public class MicroblogCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogCacheApplication.class, args);
    }

}
