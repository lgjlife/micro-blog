package com.microblog.scheduler.service;


import com.microblog.scheduler.dao.mapper.QuartzJobMapper;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.anno.QuartzJobAnno;
import com.microblog.scheduler.service.code.SchedulerReturnCode;
import com.microblog.scheduler.service.configuration.quartz.SchedulerHandle;
import com.microblog.scheduler.service.job.SchedulerJobFactory;
import com.microblog.scheduler.service.job.dto.JobState;
import com.microblog.util.result.BaseResult;
import com.microblog.util.result.ResponseCode;
import com.microblog.util.result.WebResult;
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
 *功能描述 任务管理服务
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@Service
public class SchedulerService {

    @Autowired
    private SchedulerHandle schedulerHandle;

    @Autowired
    QuartzJobMapper quartzJobMapper;


    /**
     * 对外提供的对任务的管理
     * @param type　@see SchedulerService.Handler.class
     * @param jobGroup 　任务组
     * @param jobClass　任务类
     * @return
     */
    public BaseResult manager(String type,String jobGroup,String jobClass){

        if(Handler.START_HANDLER.equals(type)){
            return resumeJob(jobGroup,jobClass);

        }
        else if(Handler.PAUSE_HANDLER.equals(type)){
            return pauseJob(jobGroup,jobClass);

        }
        else if(Handler.DELETE_HANDLER.equals(type)){
            return deleteJob(jobGroup,jobClass);
        }
        else if(Handler.REMOVE_HANDLER.equals(type)){
            return removeJob(jobGroup,jobClass);
        }
        else if(Handler.REGISTER_HANDLER.equals(type)){
            return registerJob(jobGroup,jobClass);
        }
        return new WebResult(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"请求参数出错");
    }
    /**
     * 创建任务
     * @param job
     * @return
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
     * 获取数据库中所有的任务和相应的运行状态
     * @return
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
     * 查询应用中的 被QuartzJobAnno注解的类
     * @return
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
     * 删除任务，包括数据库和quartz
     * @param jobGroup
     * @param jobClass
     * @return
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
     * 从quartz 中移除任务
     * @param jobGroup
     * @param jobClass
     * @return
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
     * 注册任务
     * @param jobGroup
     * @param jobClass
     * @return
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
     * 暂停任务运行
     * @param jobGroup
     * @param jobClass
     * @return
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
     * 暂停所有任务运行
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
     * 恢复任务运行
     * @param jobGroup
     * @param jobClass
     * @return
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
     * 恢复所有任务运行
     */
    public void resumeAllJob( )  {

        try{
            schedulerHandle.resumeAllJob();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }



    /**
     *功能描述 任务操作类型
     * @author lgj
     * @Description 　　　
     * @date 　
    */
    private class  Handler{
        /*启动*/
        private static  final  String  START_HANDLER = "startup";
        /*暂停*/
        private static  final  String  PAUSE_HANDLER = "pause";
        /*删除*/
        private static  final  String  DELETE_HANDLER = "delete";
        /*移除*/
        private static  final  String  REMOVE_HANDLER = "remove";
        /*注册*/
        private static  final  String  REGISTER_HANDLER = "register";
    }
}
