package com.microblog.log.config;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class LogKafkaProducer {

    private KafkaProducer producer;

    public LogKafkaProducer(Map<String,Object> config) {

        producer = new KafkaProducer(config);

    }

    public void   sendData(String topic,String key,String data){



        producer.send(new ProducerRecord<String, String>(topic, key, data), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("recordMetadata = " + recordMetadata);
            }
        });
    }

}
