package com.microblog.scheduler.service.configuration;


import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.job.JobFactory;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class JobConfiguration {


    @Autowired
    private Scheduler scheduler;


    @PostConstruct
    public void initJob(){

        QuartzJob job = JobFactory.quartzJob();

        log.debug("create job !");
        try {
            TriggerKey triggerKey =
                    TriggerKey.triggerKey(job.getJobClass(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            JobDetail jobDetail = null;
            try {
                //创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
                jobDetail = JobBuilder.newJob((Class<? extends org.quartz.Job>) Class.forName(job.getJobClass()))
                        .withIdentity(job.getJobClass(), job.getJobGroup()).build();

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCron());

                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobClass(), job.getJobGroup())
                        .withSchedule(scheduleBuilder).build();

                //把trigger和jobDetail注入到调度器
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (Exception ex) {
            log.error("发生异常："+ex.getMessage());
        } finally {

        }

    }
}
