package com.microblog.scheduler.service.context;

import java.util.concurrent.CopyOnWriteArrayList;


public class SchedulerContext {

    private static CopyOnWriteArrayList<String> quartzJobClass;

    public static void setQuartzJobClass(CopyOnWriteArrayList<String> list){
        quartzJobClass=list;
    }

    public static CopyOnWriteArrayList getQuartzJobClass(){
        return quartzJobClass;
    }

}
