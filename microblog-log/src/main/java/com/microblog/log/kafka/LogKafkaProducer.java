package com.microblog.log.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.HashMap;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description  kafka 生产客户端
 * @date 5/11/19
*/
public class LogKafkaProducer {

    private KafkaProducer producer;

    public LogKafkaProducer(Map<String,Object> config) {

        producer = new KafkaProducer(config);

    }

    /**
     *功能描述
     * @author lgj
     * @Description   发送数据
     * @date 5/11/19
     * @param:
     * @return:
     *
    */
    public void   sendData(String topic,String key,String data){

        producer.send(new ProducerRecord<String, String>(topic, key, data), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
               if(recordMetadata ==null){
                   throw new KafkaException("Kafka send data fail! Please check kafka is work? Maybe fail  connect to kafka server ",e.getCause());
               }
            }
        });
    }

    /**
     *功能描述
     * @author lgj
     * @Description  默认配置
     * @date 5/11/19
     * @param:
     * @return:
     *
    */
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
