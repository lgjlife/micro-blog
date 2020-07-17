package com.microblog.scheduler.web.controller;


import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.SchedulerService;
import com.microblog.util.aop.syslog.anno.PrintUrlAnno;
import com.microblog.util.result.BaseResult;
import com.microblog.util.result.ResponseCode;
import com.microblog.util.result.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *功能描述 任务处理控制器
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Api(value = "/scheduler",description = "任务调度 controller")
@Slf4j
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;


    /**
     * 创建任务
     * @param job
     * @return
     */
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
    public BaseResult jobManager(@RequestParam("type") String type,
                                 @RequestParam("jobGroup") String jobGroup,
                                 @RequestParam("jobClass") String jobClass){

        if((StringUtils.isEmpty(type))
        ||(StringUtils.isEmpty(jobGroup))
        ||(StringUtils.isEmpty(jobClass))){
            return new WebResult(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"请求参数错误");
        }

        return schedulerService.manager(type,jobGroup,jobClass);
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
