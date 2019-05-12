package com.microblog.common.aop.usetime;


import com.microblog.common.aop.usetime.aspect.PrintUseTimeAspect;
import com.microblog.common.properties.CommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(CommonProperties.class)
public class PrintUseTimeConfig {

    private static final Logger log = LoggerFactory.getLogger(PrintUseTimeConfig.class);

    @Bean
    @ConditionalOnProperty(prefix = "microblog.common", value = "printUseTimeEnable", havingValue = "true")
    public PrintUseTimeAspect printUseTimeAspect(){

        log.debug("PrintUseTimeAspect  create...........");
        return  new PrintUseTimeAspect();

    }
}
