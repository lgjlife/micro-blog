package com.microblog.scheduler.service.job;


import com.microblog.scheduler.dao.model.QuartzJob;

import java.util.Date;

public class SchedulerJobFactory {


    public static QuartzJob helloJob(){

        QuartzJob job = new QuartzJob();

       // job.setId(1);
        job.setName("hello");
        job.setDescription("hello word");
        job.setCron("0/5 * * * * ?");
        job.setJobClass(HelloJob.class.getName());
        job.setJobGroup("hello-group");
        job.setStatus("stop");
        job.setCreateTime(new Date());
        job.setCreateBy("libai");
        return  job;

    }
}
