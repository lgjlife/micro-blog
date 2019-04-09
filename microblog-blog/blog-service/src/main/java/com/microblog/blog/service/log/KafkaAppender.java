package com.microblog.blog.service.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class KafkaAppender extends AppenderBase<ILoggingEvent> {


    @Override
    public void start() {


        super.start();
        System.out.println("start.........");

        new Thread(){

            @Override
            public void run() {
                super.run();
                try{
                    Thread.sleep(2000);
                    log.debug("123");
                    System.out.println("Thread...");
                }
                catch(Exception ex){
                    log.error(ex.getMessage());
                }
            }
        }.start();

    }

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

        LogPojo logPojo = getLogPojo(iLoggingEvent);
        System.out.println(logPojo);

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
