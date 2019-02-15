package com.cloud.frame.scheduler.quartz.service;

import com.cloud.frame.scheduler.quartz.result.BaseResult;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-21 01:59
 **/
public interface SchedulerService {

    void createScheduler();
    BaseResult jobInfo();
    BaseResult jobClass();
    BaseResult jobHandleStart(String jobClass,String jobGroup);
    BaseResult jobHandleDelete(String jobClass,String jobGroup);
    BaseResult jobHandlePause(String jobClass,String jobGroup);
    BaseResult jobHandleResume(String jobClass,String jobGroup);

}
