package com.microblog.util.aop.usetime;


import com.microblog.util.aop.usetime.aspect.PrintUseTimeAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrintUseTimeConfig {

    private static final Logger log = LoggerFactory.getLogger(PrintUseTimeConfig.class);

    @Bean
    public PrintUseTimeAspect printUseTimeAspect(){

        log.debug("PrintUseTimeAspect  create...........");
        return  new PrintUseTimeAspect();

    }
}
