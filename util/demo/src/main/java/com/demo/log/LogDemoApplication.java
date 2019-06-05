package com.demo.log;

import com.log.anno.EnableKafkaLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKafkaLog
@SpringBootApplication
public class LogDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogDemoApplication.class, args);
        new MyThread(){}.start();
    }

}
