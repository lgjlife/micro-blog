package com.cloud.frame.scheduler.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: top-parent
 * @description: SpringScheduledConfig 配置类，配置后 spring的定时任务将会以多线程的方式运作
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 22:01
 **/
@Configuration
public class SpringScheduledConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(setTaskExecutors());
    }

    @Bean
    public Executor setTaskExecutors(){
        return Executors.newScheduledThreadPool(3); // 3个线程来处理。
    }

}

