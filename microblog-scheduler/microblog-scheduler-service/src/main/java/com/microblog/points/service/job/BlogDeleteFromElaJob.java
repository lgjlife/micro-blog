package com.microblog.points.service.job;


import com.microblog.points.service.anno.QuartzJobAnno;
import com.microblog.points.service.message.producer.MqProducer;
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
public class BlogDeleteFromElaJob extends AbstractJob implements Job {

    @Autowired
    private MqProducer mqProducer;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("BlogDeleteFromElaJob 任务正在运行....，" + "当前时间 = " + new Date().toString());
        try{
            mqProducer.asyncSend("message.ela","blog.delete.request","");
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }


    }
}
