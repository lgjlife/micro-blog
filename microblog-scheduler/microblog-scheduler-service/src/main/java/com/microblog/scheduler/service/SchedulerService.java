package com.microblog.scheduler.service;


import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.scheduler.dao.mapper.QuartzJobMapper;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.anno.QuartzJobAnno;
import com.microblog.scheduler.service.code.SchedulerReturnCode;
import com.microblog.scheduler.service.configuration.quartz.SchedulerHandle;
import com.microblog.scheduler.service.job.SchedulerJobFactory;
import com.microblog.scheduler.service.job.dto.JobState;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.Trigger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *功能描述 
 * @author lgj
 * @Description   任务调度service
 * @date 5/18/19
 * @param: 
 * @return: 
 *
*/
@Slf4j
@Service
public class SchedulerService {

    @Autowired
    private SchedulerHandle schedulerHandle;

    @Autowired
    QuartzJobMapper quartzJobMapper;



    /**SchedulerHandle
     *功能描述
     * @author lgj
     * @Description  创建任务
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public BaseResult createJob(QuartzJob job){
        job.setJobGroup(new Random().nextInt(1000)+"-Group");
        job.setCreateTime(new Date());
        quartzJobMapper.insert(job);
        try{
            schedulerHandle.addJod(job);
            Trigger.TriggerState state = schedulerHandle.getTriggerState(job.getJobGroup(),job.getJobClass());
            return new WebResult(SchedulerReturnCode.JOB_CREATE_SUCCESS.getCode(),
                    SchedulerReturnCode.JOB_CREATE_SUCCESS.getMessage(),
                    state.toString());

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return new WebResult(SchedulerReturnCode.JOB_CREATE_FAIL.getCode(),SchedulerReturnCode.JOB_CREATE_FAIL.getMessage());
        }
    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取数据库中所有的任务和相应的运行状态
     * @date 5/18/19
    */
    public List<QuartzJob> queryJob(){

        List<QuartzJob> quartzJobs = null;
        try{
            Map<String, JobState> jobStateMap =  schedulerHandle.queryJob();

            quartzJobs = quartzJobMapper.selectAll();

            quartzJobs.forEach((v)->{

                JobState jobState =  jobStateMap.get(v.getJobGroup()+"."+v.getJobClass());
                if(jobState == null){
                    v.setStatus(Trigger.TriggerState.NONE.toString());
                }
                else {
                    v.setStatus(jobState.getState());
                }

            });
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return null;
        }

        log.info("quartzJobs = " + quartzJobs);
        return quartzJobs;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 查询应用中的 被QuartzJobAnno注解的类
     * @date 5/18/19
    */
    public List<String> queryJobClass(){
        Reflections reflections = new Reflections("com",
                new TypeAnnotationsScanner(),
                new SubTypesScanner());


        Set<Class<?>> quartzJobs = reflections.getTypesAnnotatedWith(QuartzJobAnno.class);
        if(quartzJobs != null){
            CopyOnWriteArrayList<String> jobClassList = new CopyOnWriteArrayList<String>();
            for(Class job:quartzJobs){

                if( Job.class.isAssignableFrom(job)){
                    jobClassList.add(job.getName());
                }
            }
            return jobClassList;
        }
        return null;
    }

    public void func(){
        log.info("SchedulerService func....");
    }


    public void addJob( )  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.addJod(job);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }


    /**
     *功能描述
     * @author lgj
     * @Description  删除任务，包括数据库和quartz
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public BaseResult deleteJob(String jobGroup, String jobClass){

        Integer result = quartzJobMapper.deleteByGroupAndClass(jobGroup,jobClass);
        try{
            schedulerHandle.deleteJob(jobGroup,jobClass);
            Trigger.TriggerState state = schedulerHandle.getTriggerState(jobGroup,jobClass);
            return new WebResult(SchedulerReturnCode.JOB_DELETE_SUCCESS.getCode(),
                    SchedulerReturnCode.JOB_DELETE_SUCCESS.getMessage(),
                    state.toString());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return new WebResult(SchedulerReturnCode.JOB_DELETE_FAIL.getCode(),
                    SchedulerReturnCode.JOB_DELETE_FAIL.getMessage());
        }

    }
    /**
     *功能描述
     * @author lgj
     * @Description 从quartz 中移除任务
     * @date 5/18/19
    */
    public BaseResult removeJob(String jobGroup, String jobClass)   {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.deleteJob(jobGroup,jobClass);
            Trigger.TriggerState state = schedulerHandle.getTriggerState(jobGroup,jobClass);
            return new WebResult(SchedulerReturnCode.JOB_REMOVE_SUCCESS.getCode(),
                    SchedulerReturnCode.JOB_REMOVE_SUCCESS.getMessage(),state.toString());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return new WebResult(SchedulerReturnCode.JOB_REMOVE_FAIL.getCode(),SchedulerReturnCode.JOB_REMOVE_FAIL.getMessage());
        }

    }
    /**
     *功能描述
     * @author lgj
     * @Description  注册任务
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public BaseResult registerJob(String jobGroup, String jobClass)   {
        QuartzJob job = quartzJobMapper.selectByGroupAndClass(jobGroup,jobClass);
        try{
            schedulerHandle.addJod(job);
            Trigger.TriggerState state = schedulerHandle.getTriggerState(job.getJobGroup(),job.getJobClass());
            return new WebResult(SchedulerReturnCode.JOB_CREATE_SUCCESS.getCode(),
                    SchedulerReturnCode.JOB_CREATE_SUCCESS.getMessage(),state.toString());

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return new WebResult(SchedulerReturnCode.JOB_CREATE_FAIL.getCode(),
                    SchedulerReturnCode.JOB_CREATE_FAIL.getMessage());
        }

    }

    /**
     *功能描述
     * @author lgj
     * @Description  暂停任务运行
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public BaseResult pauseJob( String jobGroup,String jobClass)  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.pauseJob(jobGroup,jobClass);
            Trigger.TriggerState state = schedulerHandle.getTriggerState(jobGroup,jobClass);
            return new WebResult(SchedulerReturnCode.JOB_PAUSE_SUCCESS.getCode(),
                    SchedulerReturnCode.JOB_PAUSE_SUCCESS.getMessage(),
                    state.toString());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return new WebResult(SchedulerReturnCode.JOB_PAUSE_FAIL.getCode(),
                    SchedulerReturnCode.JOB_PAUSE_FAIL.getMessage());
        }

    }

    /**
     *功能描述
     * @author lgj
     * @Description  暂停所有任务运行
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public void pauseAllJob( )  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.pauseAllJob();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }

    /**
     *功能描述
     * @author lgj
     * @Description 恢复任务运行
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public BaseResult resumeJob( String jobGroup,String jobClass)  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.resumeJob(jobGroup,jobClass);
            Trigger.TriggerState state = schedulerHandle.getTriggerState(jobGroup,jobClass);
            return new WebResult(SchedulerReturnCode.JOB_STARTUP_SUCCESS.getCode(),
                    SchedulerReturnCode.JOB_STARTUP_SUCCESS.getMessage(),
                    state.toString());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return new WebResult(SchedulerReturnCode.JOB_STARTUP_FAIL.getCode(),
                    SchedulerReturnCode.JOB_STARTUP_FAIL.getMessage());
        }
    }

    /**
     *功能描述
     * @author lgj
     * @Description 　恢复所有任务运行
     * @date 5/18/19
     * @param:
     * @return:
     *
    */
    public void resumeAllJob( )  {

        try{
            schedulerHandle.resumeAllJob();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }
}
