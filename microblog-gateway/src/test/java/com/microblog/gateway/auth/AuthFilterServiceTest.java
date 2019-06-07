package com.microblog.gateway.auth;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

public class AuthFilterServiceTest {

    @Test
    public void needFilter() {

        AntPathMatcher matcher =  new AntPathMatcher();
        System.out.println(matcher.match("/user/user/info/**","/user/user/info/header-img")); //true


    }
}