package com.microblog.scheduler.web.controller;

import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.SchedulerService;
import com.microblog.scheduler.service.code.SchedulerReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;


    @PrintUrlAnno
    @RequestMapping("/job")
    public BaseResult create(@RequestBody QuartzJob job){

        log.info("job = " + job);
        return schedulerService.createJob(job);
    }

    @PrintUrlAnno
    @PostMapping("/job/manager")
    @ResponseBody
    public BaseResult jobManager(@RequestBody Map<String ,String> requestMap){
        String type = requestMap.get("type");
        String jobGroup = requestMap.get("jobGroup");
        String jobClass = requestMap.get("jobClass");
        if((StringUtils.isEmpty(type))
        ||(StringUtils.isEmpty(jobGroup))
        ||(StringUtils.isEmpty(jobClass))){
            return new WebResult(SchedulerReturnCode.NULL_PARAM);
        }

        if(type.equals("startup")){
            return schedulerService.resumeJob(jobGroup,jobClass);

        }
        else if(type.equals("pause")){
            return schedulerService.pauseJob(jobGroup,jobClass);

        }
        else if(type.equals("delete")){
            return schedulerService.deleteJob(jobGroup,jobClass);
        }
        else if(type.equals("remove")){
            return schedulerService.removeJob(jobGroup,jobClass);
        }

        return new WebResult(SchedulerReturnCode.NULL_PARAM);

    }

    @GetMapping("/list")
    public List<QuartzJob> queryJob(){
        return schedulerService.queryJob();
    }

    @GetMapping("/job/class/list")
    public List<String> queryJobClass(){
        return schedulerService.queryJobClass();
    }


    @RequestMapping("/add")
    public void addJob(){

        schedulerService.addJob();
    }



    @RequestMapping("/pause/all")
    public void pauseAllJob(){

        schedulerService.pauseAllJob();
    }

    @RequestMapping("/resume/all")
    public void resumeAllJob(){

        schedulerService.resumeAllJob();
    }


}
