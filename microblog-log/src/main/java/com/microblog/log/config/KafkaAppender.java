package com.microblog.log.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSONObject;
import com.microblog.log.kafka.LogKafkaProducer;
import com.microblog.log.pojo.LogPojo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
//@Component
@Data
public class KafkaAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {


    protected String topic = "application-log";
    protected Map<String,Object> producerCfg =  new HashMap<String, Object>();

    LogKafkaProducer producer ;

    AtomicInteger count = new AtomicInteger(0);

    private  String applicationName;

    public KafkaAppender() {
    }


    @Override
    public void start() {
        super.start();
        producer = new LogKafkaProducer(producerCfg);
    }

    @Override
    protected void append(ILoggingEvent event) {

        String obj = JSONObject.toJSONString(getLogPojo(event));
        producer.sendData(topic,"log-group",obj);
    }

    private  LogPojo getLogPojo(ILoggingEvent event){
        StringBuilder builder = new StringBuilder();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time =  format.format(new Date(event.getTimeStamp()));

        LogPojo logPojo = LogPojo.builder().datetime(time)
                .level(event.getLevel().levelStr)
                .thread(event.getThreadName())
                .method(event.getLoggerName())
                .message(event.getMessage())
                .build();
        return  logPojo;
    }

    public void setProducerCfg(Map<String, Object> producerCfg) {
        this.producerCfg = producerCfg;
    }

    public void addProducerCfg(String keyValue) {
        String[] split = keyValue.split("=", 2);
        if(split.length == 2)
            addProducerCfgValue(split[0], split[1]);
    }

    public void addProducerCfgValue(String key, Object value) {
        this.producerCfg.put(key,value);
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }

}
