package com.microblog.scheduler.service;


import com.microblog.scheduler.dao.mapper.QuartzJobMapper;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.configuration.quartz.SchedulerHandle;
import com.microblog.scheduler.service.job.SchedulerJobFactory;
import com.microblog.scheduler.service.job.dto.JobState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class SchedulerService {

    @Autowired
    private SchedulerHandle schedulerHandle;


    @Autowired
    QuartzJobMapper quartzJobMapper;


    public void  createJob(){

        log.info("SchedulerService addScheduler....");
        QuartzJob job = SchedulerJobFactory.helloJob();

        log.info("job = " + job);
        quartzJobMapper.insert(job);
    }

    public Map<String, JobState> queryJob(){
        try{
            return schedulerHandle.queryJob();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return null;
        }

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
