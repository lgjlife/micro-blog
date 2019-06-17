package com.microblog.points.service.job;

import com.microblog.points.service.SchedulerService;
import com.microblog.points.service.anno.QuartzJobAnno;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 11:34
 **/

@QuartzJobAnno
public class HelloJob extends AbstractJob implements Job {

    @Autowired
    SchedulerService schedulerService;

    public static String description = "我是HelloJob";


    private  static  final Logger log = LoggerFactory.getLogger(HelloJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        JobKey key = jobExecutionContext.getJobDetail().getKey();
        log.info("[{}] Hello 任务正在运行....，" + "当前时间 = {}",key,new Date().toString());
    }
}
