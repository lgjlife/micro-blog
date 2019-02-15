package com.cloud.frame.scheduler.quartz.spring.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 21:42
 **/

@Component
public class MyJob {

    private  static  final Logger log = LoggerFactory.getLogger(MyJob.class);
   // @Scheduled(cron = "0/10 * * * * ?")
    public void run(){

        log.info("run .....");
    }
}
