package com.microblog.scheduler.web.controller;

import com.microblog.scheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;


    @RequestMapping("/create")
    public void create(){

        schedulerService.createJob();
    }

    @RequestMapping("/add")
    public void addJob(){

        schedulerService.addJob();
    }

    @RequestMapping("/delete")
    public void deleteJob(){

        schedulerService.deleteJob();
    }

    @RequestMapping("/pause")
    public void pauseJob(){

        schedulerService.pauseJob();
    }

    @RequestMapping("/pause/all")
    public void pauseAllJob(){

        schedulerService.pauseAllJob();
    }

    @RequestMapping("/resume")
    public void resumeJob(){

        schedulerService.resumeJob();
    }

    @RequestMapping("/resume/all")
    public void resumeAllJob(){

        schedulerService.resumeAllJob();
    }


}