package com.microblog.log;


import com.microblog.log.anno.EnableKafkaLog;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
class MyThread extends  Thread{

    @Override
    public void run() {

        int i = 0;
       while (true){



           log.info("MyThread ..." + i ++);
           try{
               Thread.sleep(4000);
           }
           catch(Exception ex){
               log.error(ex.getMessage());
           }

       }
    }
}
