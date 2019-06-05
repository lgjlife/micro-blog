package com.log.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class KafkaConfig {

    private final String defaultTopic = "application-log";
    private final String defaultAppName = "default-application";

    public abstract Map<String,Object> config();

    public abstract String appName();

    public abstract String topic();

    public Map<String,Object> getConfig(){

        Map<String,Object> config = config();
        Map<String,Object> defaltConfig = defaultConfig();

        if((config == null) || (config.size() == 0)){
            return defaultConfig();
        }
        config.forEach((key,value)->{
            defaltConfig.put(key,value);
        });
        return defaltConfig;


    }

    public String getAppName(){
        return (appName()==null)?defaultAppName:appName();
    }

    public String getTopic(){
        return (topic()==null)?defaultTopic:topic();
    }

    public static Map<String ,Object> defaultConfig(){

        Map<String,Object> kafkaCfg = new HashMap<>();
        kafkaCfg.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093");
        kafkaCfg.put(ProducerConfig.ACKS_CONFIG,"all");
        kafkaCfg.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,1000);
        kafkaCfg.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG,1000);
        kafkaCfg.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,1000);
        kafkaCfg.put(ProducerConfig.RETRIES_CONFIG,1);
        kafkaCfg.put(ProducerConfig.BATCH_SIZE_CONFIG,16);
        kafkaCfg.put(ProducerConfig.LINGER_MS_CONFIG,5);
        kafkaCfg.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        kafkaCfg.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        kafkaCfg.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");


        return  kafkaCfg;

    }




}
