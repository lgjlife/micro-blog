package com.microblog.log.config;

import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.encoder.Encoder;

import java.io.IOException;

public class KafkaOutputStreamAppender<ILoggingEvent> extends OutputStreamAppender<ILoggingEvent> {


    @Override
    public void start() {

        super.start();
    }

    @Override
    protected void append(ILoggingEvent event) {
        super.append(event);

        System.out.println(event);
    }

    @Override
    public void setEncoder(Encoder encoder) {
        super.setEncoder(encoder);
    }

    @Override
    protected void writeOut(ILoggingEvent event) throws IOException {

        System.out.println("writeOut");

        super.writeOut(event);
    }

    @Override
    protected void subAppend(ILoggingEvent event) {


        System.out.println("subAppend");
        super.subAppend(event);
    }
}
