package com.log.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSONObject;
import com.log.kafka.LogKafkaProducer;
import com.log.pojo.LogPojo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Data
public class KafkaAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {


    protected String topic = "application-log";
    protected Map<String,Object> producerCfg =  new HashMap<String, Object>();
    private ExecutorService logSendTaskService = Executors.newCachedThreadPool();
    private LogKafkaProducer producer ;
    private  String applicationName;

    public KafkaAppender() {
    }


    @Override
    public void start() {
        super.start();
    }

    @Override
    protected void append(ILoggingEvent event) {
        logSendTaskService.submit(new LogTask(event));
    }

    private LogPojo getLogPojo(ILoggingEvent event){
        StringBuilder builder = new StringBuilder();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time =  format.format(new Date(event.getTimeStamp()));

        LogPojo logPojo = LogPojo.builder().datetime(time)
                .level(event.getLevel().levelStr)
                .thread(event.getThreadName())
                .method(event.getLoggerName())
                .message(event.getMessage())
                .appName(applicationName)
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

    public void setProducer(LogKafkaProducer producer) {
        this.producer = producer;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    class LogTask implements Runnable{

        private ILoggingEvent event;
        private String group = "log-group";

        public LogTask(ILoggingEvent event) {
            this.event = event;
        }

        public LogTask(ILoggingEvent event, String group) {
            this.event = event;
            this.group = group;
        }

        @Override
        public void run() {
            String logData = JSONObject.toJSONString(getLogPojo(event));
            producer.sendData(topic,group,logData);
        }
    }

}
