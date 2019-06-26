package com.microblog.scheduler.web.controller;

import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.SchedulerService;
import com.microblog.scheduler.service.code.SchedulerReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(value = "/scheduler",description = "任务调度 controller")
@Slf4j
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;



    @PrintUrlAnno
    @RequestMapping("/job")
    @ApiOperation(value = "/job",httpMethod = "POST",notes="创建任务")
    public BaseResult create(@RequestBody QuartzJob job){

        log.info("job = " + job);
        return schedulerService.createJob(job);
    }

    @PrintUrlAnno
    @PostMapping("/job/manager")
    @ResponseBody
    @ApiOperation(value = "/job/manager",httpMethod = "POST",notes="任务管理")
    public BaseResult jobManager(@RequestBody Map<String ,String> requestMap){
        String type = requestMap.get("type");
        String jobGroup = requestMap.get("jobGroup");
        String jobClass = requestMap.get("jobClass");
        if((StringUtils.isEmpty(type))
        ||(StringUtils.isEmpty(jobGroup))
        ||(StringUtils.isEmpty(jobClass))){
            return new WebResult(SchedulerReturnCode.NULL_PARAM.getCode(),SchedulerReturnCode.NULL_PARAM.getMessage());
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
        else if(type.equals("register")){
            return schedulerService.registerJob(jobGroup,jobClass);
        }

        return new WebResult(SchedulerReturnCode.NULL_PARAM.getCode(),SchedulerReturnCode.NULL_PARAM.getMessage());

    }

    @ApiOperation(value = "/list",httpMethod = "GET",notes="获取任务列表")
    @GetMapping("/list")
    public List<QuartzJob> queryJob(){
        return schedulerService.queryJob();
    }

    @ApiOperation(value = "/job/class/list",httpMethod = "GET",notes="获取任务类列表")
    @GetMapping("/job/class/list")
    public List<String> queryJobClass(){
        return schedulerService.queryJobClass();
    }

    @ApiOperation(value = "/list",httpMethod = "POST",notes="添加任务")
    @RequestMapping("/add")
    public void addJob(){
        schedulerService.addJob();
    }


    @ApiOperation(value = "/list",httpMethod = "POST",notes="暂停任务")
    @RequestMapping("/pause/all")
    public void pauseAllJob(){

        schedulerService.pauseAllJob();
    }

    @ApiOperation(value = "/list",httpMethod = "POST",notes="恢复任务执行")
    @RequestMapping("/resume/all")
    public void resumeAllJob(){

        schedulerService.resumeAllJob();
    }


}
