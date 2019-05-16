package com.microblog.scheduler.service.configuration.quartz;

import com.microblog.scheduler.dao.model.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;


/**
 *功能描述
 * @author lgj
 * @Description  quartz 任务操作类
 * @date 5/16/19
*/
@Slf4j
public class SchedulerHandle {


    private Scheduler scheduler;

    public SchedulerHandle(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  添加任务　
     * @date 5/16/19
     * @param:  　QuartzJob
     * @return: 　true:　任务添加成功　　false：　任务添加失败
     *　　　　　　　
    */
    public boolean addJod(QuartzJob job) throws ClassNotFoundException, SchedulerException {

        TriggerKey triggerKey =
                TriggerKey.triggerKey(job.getJobClass(), job.getJobGroup());


           //创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends org.quartz.Job>) Class.forName(job.getJobClass()))
                .withIdentity(job.getJobClass(), job.getJobGroup()).build();

        //hello-group.com.microblog.scheduler.dao.model.QuartzJob
        //hello-group.com.microblog.scheduler.service.job.HelloJob
        log.info("job key = [{}]",jobDetail.getKey());
        if(scheduler.checkExists(jobDetail.getKey()) == true){
            log.info("job[{}] is exist!!",jobDetail.getKey());
            return false;
        }


        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        //按新的cronExpression表达式构建一个新的trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobClass(), job.getJobGroup())
                .withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();

        //把trigger和jobDetail注入到调度器
        scheduler.scheduleJob(jobDetail, trigger);
        return true;

    }

    /**
     *功能描述
     * @author lgj
     * @Description  删除任务
     * @date 5/16/19
     * @param:
     * @return:
     *
    */
    public boolean deleteJob(QuartzJob job) throws SchedulerException{
        JobKey jobKey = JobKey.jobKey(job.getJobClass(),job.getJobGroup());

        if(scheduler.checkExists(jobKey)){
            log.debug("deleteJob:[{}],Exist!",jobKey.toString());
            scheduler.deleteJob(jobKey);
            return true;
        }
        log.debug("deleteJob:[{}],Not Exist!",jobKey.toString());
        return false;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  暂停任务
     * @date 5/16/19
     * @param:
     * @return:
     *
    */
    public void pauseJob(QuartzJob job) throws SchedulerException{

        JobKey jobKey = JobKey.jobKey(job.getJobClass(),job.getJobGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobClass(), job.getJobGroup());
        if(scheduler.checkExists(jobKey)){
            log.debug("pauseJob:[{}],Exist!",jobKey.toString());
            scheduler.pauseJob(jobKey);
            scheduler.pauseTrigger(triggerKey);
            return;
        }
        log.debug("pauseJob:[{}],Not Exist!",jobKey.toString());
    }
    public void pauseAllJob() throws SchedulerException{
        log.debug("pause　all Job");
        scheduler.pauseAll();

    }


    /**
     *功能描述 
     * @author lgj
     * @Description 恢复任务
     * @date 5/16/19
     * @param: 
     * @return: 
     *
    */
    public void resumeJob(QuartzJob job) throws SchedulerException{
        JobKey jobKey = JobKey.jobKey(job.getJobClass(),job.getJobGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobClass(), job.getJobGroup());
        if(scheduler.checkExists(jobKey)){
            log.debug("resumeJob:[{}],Exist!",jobKey.toString());
            scheduler.resumeJob(jobKey);
            scheduler.resumeTrigger(triggerKey);
            return;
        }
        log.debug("resumeJob:[{}],Not Exist!",jobKey.toString());
    }

    public void resumeAllJob( ) throws SchedulerException{
        scheduler.resumeAll();
    }


}