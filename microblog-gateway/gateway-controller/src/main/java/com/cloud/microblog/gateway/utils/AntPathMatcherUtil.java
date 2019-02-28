package com.cloud.microblog.gateway.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;


@Slf4j
public class AntPathMatcherUtil {

    public static void main(String args[]){
        AntPathMatcher matcher =  new AntPathMatcher();

        String pattern = "/a/**/c";
        String path = "/a/b";

        boolean result = matcher.match(pattern,path);

        log.debug("result = {}",result);

    }
}
