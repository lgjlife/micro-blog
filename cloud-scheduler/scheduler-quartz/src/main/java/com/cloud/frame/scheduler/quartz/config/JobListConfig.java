package com.cloud.frame.scheduler.quartz.config;

import com.cloud.frame.scheduler.quartz.job.HelloJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-21 01:39
 **/
@Configuration
public class JobListConfig {

    @Bean("jobList")
    public ArrayList<JobDesc> job(){
        ArrayList<JobDesc> list = new ArrayList<JobDesc>();

        list.add(new JobDesc(HelloJob.class.getName(),HelloJob.description));


        return list;
    }
}
