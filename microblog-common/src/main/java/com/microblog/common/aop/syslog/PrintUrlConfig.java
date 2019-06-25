package com.microblog.common.aop.syslog;


import com.microblog.common.aop.syslog.aspect.PrintUrlAspect;
import com.microblog.common.properties.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Slf4j
@Configuration
@EnableConfigurationProperties(CommonProperties.class)
public class PrintUrlConfig {

    private static final Logger log = LoggerFactory.getLogger(PrintUrlConfig.class);
    @Bean
    @ConditionalOnProperty(prefix = "microblog.common", value = "printUrlEnable", havingValue = "true")
    public PrintUrlAspect printUrlAspect(){

        log.debug("PrintUrlAspect  create...........");
        return  new PrintUrlAspect();

    }
}
