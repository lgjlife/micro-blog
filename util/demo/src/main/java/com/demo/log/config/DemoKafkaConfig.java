package com.demo.log.config;

import com.log.kafka.KafkaConfig;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DemoKafkaConfig extends KafkaConfig {

    @Override
    public Map<String, Object> config() {
        return null;
    }

    @Override
    public String appName() {
        return "DemoApplication";
    }

    @Override
    public String topic() {
        return "application-log";
    }
}
