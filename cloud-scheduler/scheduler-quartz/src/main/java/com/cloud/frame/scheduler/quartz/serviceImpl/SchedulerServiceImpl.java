package com.cloud.frame.scheduler.quartz.serviceImpl;

import com.cloud.frame.scheduler.quartz.code.SchedulerReturnCode;
import com.cloud.frame.scheduler.quartz.config.JobDesc;
import com.cloud.frame.scheduler.quartz.dao.mapper.QuartzJobMapper;
import com.cloud.frame.scheduler.quartz.dao.model.QuartzJob;
import com.cloud.frame.scheduler.quartz.job.HelloJob1;
import com.cloud.frame.scheduler.quartz.result.BaseResult;
import com.cloud.frame.scheduler.quartz.result.WebResult;
import com.cloud.frame.scheduler.quartz.service.SchedulerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 11:31
 **/

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private  static  final Logger log = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Resource(name = "jobList")
    ArrayList<JobDesc> joblist;

    @Autowired
    private Scheduler scheduler;


    @Autowired
    QuartzJobMapper quartzJobMapper;

    @Override
    public void createScheduler(){

        try {

            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

            Scheduler sched = schedFact.getScheduler();

            sched.start();


            JobDetail jobDetail = newJob(HelloJob1.class)
                    .withIdentity("myJob","group")
                    .usingJobData("mykey","mydata")
                    .build();

            Trigger trigger = newTrigger().withIdentity("myTrigger","group")
                    .startNow().forJob(jobDetail)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            Trigger trigger1 = newTrigger().withIdentity("myTrigger","group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/10 * * * * ?")
                    )
                    .build();


            log.info("Key = " + trigger.getKey() + "  JobKey = " + trigger.getJobKey());
            sched.scheduleJob(jobDetail, trigger1);

            log.info("createScheduler 启动任务.....");

        } catch (Exception ex) {

        } finally{    }


    }

    /** 
     * @description:  获取job的信息
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @Override
    public BaseResult jobInfo() {

        List<QuartzJob> jobs = quartzJobMapper.selectAll();

        return new WebResult(SchedulerReturnCode.REQUEST_OK,jobs);

    }

    /** 
     * @description:  获取 job的实现类 
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @Override
    public BaseResult jobClass() {

        log.info("joblist len = " + joblist.size() );
        for(JobDesc job:joblist){
            log.info(job.toString());
        }
        return new WebResult(SchedulerReturnCode.REQUEST_OK,joblist);
    }

    /** 
     * @description:  启动任务 
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @Override
    public BaseResult jobHandleStart(String jobClass, String jobGroup) {
        log.info(jobGroup + "---" + jobClass + "任务启动.....");
        try {
            TriggerKey triggerKey =
                    TriggerKey.triggerKey(jobClass, jobGroup);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            JobDetail jobDetail = null;
            try {
                //创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
                jobDetail = JobBuilder.newJob((Class<? extends org.quartz.Job>) Class.forName(jobClass))
                        .withIdentity(jobClass, jobGroup).build();

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(jobClass, jobGroup)
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


        return null;
    }

    /** 
     * @description:  删除任务 
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @Override
    public BaseResult jobHandleDelete(String jobClass, String jobGroup) {

        log.info(jobGroup + "---" + jobClass + "任务删除.....");

        try {
            JobKey jobKey = new JobKey(jobClass,jobGroup);
            scheduler.deleteJob(jobKey);
        } catch (Exception ex) {
            log.error("jobHandleStart 发生错误：" + ex.getMessage());
        } finally {

        }

        return null;
    }

    @Override
    public BaseResult jobHandlePause(String jobClass, String jobGroup) {
        log.info(jobGroup + "---" + jobClass + "任务挂起.....");
        try {
            JobKey jobKey = new JobKey(jobClass,jobGroup);
            scheduler.pauseJob(jobKey);
        } catch (Exception ex) {
            log.error("jobHandlePause 发生错误：" + ex.getMessage());
        } finally {

        }

        return null;
    }

    @Override
    public BaseResult jobHandleResume(String jobClass, String jobGroup) {
        log.info(jobGroup + "---" + jobClass + "任务恢复.....");
        try {
            JobKey jobKey = new JobKey(jobClass,jobGroup);
            scheduler.resumeJob(jobKey);
        } catch (Exception ex) {
            log.error("jobHandleResume 发生错误：" + ex.getMessage());
        } finally {

        }

        return null;
    }
}
