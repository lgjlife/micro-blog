package com.microblog.scheduler.service.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 11:34
 **/
public class BlogLikeJob extends AbstractJob implements Job {

    public static String description = "我是HelloJob";


    private  static  final Logger log = LoggerFactory.getLogger(BlogLikeJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("BlogLikeJob 任务正在运行....，" + "当前时间 = " + new Date().toString());


        JobKey key = jobExecutionContext.getJobDetail().getKey();

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String jobSays = dataMap.getString("mykey");

        log.info("mydata = " + jobSays);
    }
}
