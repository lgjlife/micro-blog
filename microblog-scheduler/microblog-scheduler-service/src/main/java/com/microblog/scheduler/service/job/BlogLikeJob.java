package com.microblog.scheduler.service.job;

import com.microblog.scheduler.service.anno.QuartzJobAnno;
import com.microblog.scheduler.service.message.producer.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
@Slf4j
public class BlogLikeJob extends AbstractJob implements Job {

    @Autowired
    private MqProducer mqProducer;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("BlogLikeJob 任务正在运行....，" + "当前时间 = " + new Date().toString());
        try{
            mqProducer.asyncSend("message:blog:like:import:enable","like.import.enable","");
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }


    }
}
