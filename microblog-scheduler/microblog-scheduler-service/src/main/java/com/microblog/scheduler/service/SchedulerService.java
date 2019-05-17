package com.microblog.scheduler.service;


import com.microblog.scheduler.dao.mapper.QuartzJobMapper;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.anno.QuartzJobAnno;
import com.microblog.scheduler.service.configuration.quartz.SchedulerHandle;
import com.microblog.scheduler.service.context.SchedulerContext;
import com.microblog.scheduler.service.job.SchedulerJobFactory;
import com.microblog.scheduler.service.job.dto.JobState;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
public class SchedulerService {

    @Autowired
    private SchedulerHandle schedulerHandle;

    @Autowired
    ApplicationContext context;

    @Autowired
    QuartzJobMapper quartzJobMapper;


    public void  createJob(){

        log.info("SchedulerService addScheduler....");
        QuartzJob job = SchedulerJobFactory.helloJob();

        log.info("job = " + job);
        quartzJobMapper.insert(job);
    }

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

        return quartzJobs;
    }

    public List<String> queryJobClass(){

        if(CollectionUtils.isEmpty(SchedulerContext.getQuartzJobClass()) == false){

            return SchedulerContext.getQuartzJobClass();
        }

        Reflections reflections = new Reflections("com",
                new TypeAnnotationsScanner(),
                new SubTypesScanner());


        Set<Class<?>> quartzJobs = reflections.getTypesAnnotatedWith(QuartzJobAnno.class);
        if(quartzJobs != null){
            CopyOnWriteArrayList<String> jobClassList = new CopyOnWriteArrayList<String>();
            for(Class job:quartzJobs){
                jobClassList.add(job.getName());
            }
            SchedulerContext.setQuartzJobClass(jobClassList);
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


    public void deleteJob( )   {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.deleteJob(job);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }

    public void pauseJob( )  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.pauseJob(job);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }

    public void pauseAllJob( )  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.pauseAllJob();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }

    public void resumeJob( )  {
        QuartzJob job = SchedulerJobFactory.helloJob();
        try{
            schedulerHandle.resumeJob(job);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }

    public void resumeAllJob( )  {

        try{
            schedulerHandle.resumeAllJob();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }






}
