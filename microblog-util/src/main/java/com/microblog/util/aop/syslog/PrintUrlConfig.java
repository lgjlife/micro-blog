package com.microblog.util.aop.syslog;


import com.microblog.util.aop.syslog.aspect.PrintUrlAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Slf4j
@Configuration
public class PrintUrlConfig {

    private static final Logger log = LoggerFactory.getLogger(PrintUrlConfig.class);
    @Bean
    public PrintUrlAspect printUrlAspect(){

        log.debug("PrintUrlAspect  create...........");
        return  new PrintUrlAspect();

    }
}
