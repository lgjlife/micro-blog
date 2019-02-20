package com.cloud.microblog.gateway.service.scheduled;

import com.cloud.microblog.gateway.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledConfig {


    @Autowired
    UserService userService;

 //   @Scheduled(cron = "0/10 * *  * * * ")
    public void reportCurrentByCron(){
        log.debug("任务执行中");

    }
}
