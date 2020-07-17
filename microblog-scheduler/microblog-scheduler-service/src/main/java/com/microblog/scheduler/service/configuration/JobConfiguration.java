package com.microblog.scheduler.service.configuration;


import com.microblog.scheduler.dao.mapper.QuartzJobMapper;
import com.microblog.scheduler.dao.model.QuartzJob;
import com.microblog.scheduler.service.configuration.quartz.SchedulerHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *功能描述
 * @author lgj
 * @Description  启动时从数据库中获取任务列表执行
 * @date
*/
@Slf4j
@Configuration
public class JobConfiguration {


    @Autowired
    private SchedulerHandle schedulerHandle;

    @Autowired
    QuartzJobMapper quartzJobMapper;


   // @PostConstruct
    public void initJob(){
        List<QuartzJob> jobs =  quartzJobMapper.selectAll();
        if(CollectionUtils.isEmpty(jobs)){
            log.info("Scheduler can not find one Job to be do!");
            return;
        }

        for(QuartzJob job:jobs){
            try {
                schedulerHandle.addJod(job);

            } catch (Exception ex) {
                log.error("发生异常："+ex.getMessage());
                ex.printStackTrace();
            }
        }

    }
}
