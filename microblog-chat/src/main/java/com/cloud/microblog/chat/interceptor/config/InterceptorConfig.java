package com.cloud.microblog.chat.interceptor.config;


import com.cloud.microblog.chat.interceptor.AcecssInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig  extends WebMvcConfigurationSupport {

    @Autowired
    AcecssInterceptor acecssInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(acecssInterceptor).addPathPatterns("/**");
    }



}
