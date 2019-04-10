package com.microblog.log.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.microblog.log.pojo.LogPojo;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
//@Component
public class KafkaAppender extends AppenderBase<ILoggingEvent> {

    AtomicInteger count = new AtomicInteger(0);

    @Override
    protected void append(ILoggingEvent event) {

        LogPojo logPojo = getLogPojo(event);
        System.out.println(logPojo);
        System.out.println("count = " + count.addAndGet(1));

      //  event = null;
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
}
