package com.microblog.log;


import com.microblog.log.anno.EnableKafkaLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKafkaLog
@SpringBootApplication
public class MicroblogLogApplication {

    public static void main(String args[]){
        SpringApplication.run(MicroblogLogApplication.class,args);
        new MyThread(){}.start();
    }
}


