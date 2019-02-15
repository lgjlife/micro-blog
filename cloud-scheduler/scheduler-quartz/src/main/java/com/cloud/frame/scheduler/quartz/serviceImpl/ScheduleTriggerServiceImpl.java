package com.cloud.frame.scheduler.quartz.serviceImpl;

import com.cloud.frame.scheduler.quartz.dao.mapper.QuartzJobMapper;
import com.cloud.frame.scheduler.quartz.dao.model.QuartzJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 17:47
 **/

@Service
public class ScheduleTriggerServiceImpl {

    private  static  final Logger log = LoggerFactory.getLogger(SchedulerServiceImpl.class);


    @Autowired
    private Scheduler scheduler;

    @Autowired
    QuartzJobMapper quartzJobMapper;

    public void refreshTrigger(){

        log.info("refreshTrigger  running.......");
        List<QuartzJob> jobs = quartzJobMapper.selectAll();
        if(jobs == null){
            return;
        }

        for(QuartzJob job:jobs){
            log.info(job.toString());

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

}
