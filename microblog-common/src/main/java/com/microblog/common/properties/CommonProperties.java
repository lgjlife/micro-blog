package com.microblog.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "microblog.common")
public class CommonProperties {

    private String printUrlEnable;

    private String printUseTimeEnable;



}
