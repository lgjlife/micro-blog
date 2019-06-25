package com.microblog.log.properties;


import com.microblog.log.kafka.LogKafkaProducer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@Data
@ConfigurationProperties(prefix ="logappender")
public class LogAppenderProperties {

    private String topic ="application-log";
    private Map<String,Object> kafkaCfg = LogKafkaProducer.defaultConfig();
}
