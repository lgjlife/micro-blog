package com.cloud.frame.scheduler.quartz.controller;

import com.cloud.frame.scheduler.quartz.result.BaseResult;
import com.cloud.frame.scheduler.quartz.service.SchedulerService;
import com.cloud.frame.scheduler.quartz.serviceImpl.ScheduleTriggerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 11:41
 **/


@Api("/scheduler/run")
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    private  static  final Logger log = LoggerFactory.getLogger(SchedulerController.class);


    @Autowired
    SchedulerService schedulerService;

    @Autowired
    ScheduleTriggerServiceImpl scheduleTriggerService;

    @ApiOperation(value="/create",notes = "根据ID获取用户",httpMethod="GET")
    @GetMapping("/create")
    public  void createScheduler(){
        schedulerService.createScheduler();
    }

    @ApiOperation(value="/run",notes = "运行任务",httpMethod="GET")
    @GetMapping("/run")
    public void runJob(){
        scheduleTriggerService.refreshTrigger();
    }


    /** 
     * @description:  
     * @param: 获取所有的job类信息 
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @ApiOperation(value="/jobclass",notes = "获取类信息",httpMethod="GET")
    @GetMapping("/jobclass")
    public BaseResult jobClass(){
        return schedulerService.jobClass();
    }
    /** 
     * @description:  获取任务的信息
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @ApiOperation(value="/info",notes = "获取任务信息",httpMethod="GET")
    @GetMapping("/info")
    public BaseResult jobInfo(){

        return schedulerService.jobInfo();
    }

    /** 
     * @description:  任务操作  停止/启动/删除
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 11/21/18 
    */ 
    @ApiOperation(value="/handle",notes = "任务操作",httpMethod="GET")
    @PostMapping("/handle")
    public BaseResult jobHandle(@RequestBody Map<String,String> data){

        String jobClass = data.get("jobClass");
        String jobGroup = data.get("jobGroup");
        String jobCommand = data.get("jobCommand");

        log.info("jobClass = " + jobClass
                  + "  jobGroup = " + jobGroup
                  + "  jobCommand = " +  jobCommand);


        if(jobCommand.equals("启动")){
            return schedulerService.jobHandleStart( jobClass, jobGroup);
        }
        else if(jobCommand.equals("删除")){
            return schedulerService.jobHandleDelete( jobClass, jobGroup);
        }
        else if(jobCommand.equals("挂起")){
            return schedulerService.jobHandlePause( jobClass, jobGroup);
        }
        else if(jobCommand.equals("恢复")){
            return schedulerService.jobHandleResume( jobClass, jobGroup);
        }


        return null;
    }




}
