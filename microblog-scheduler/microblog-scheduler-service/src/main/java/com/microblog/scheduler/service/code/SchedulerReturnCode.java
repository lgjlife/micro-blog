package com.microblog.scheduler.service.code;


import com.microblog.util.code.ReturnCode;

public enum  SchedulerReturnCode implements ReturnCode {


    NULL_PARAM(0,"请求参数为空!"),

    JOB_STARTUP_SUCCESS(1,"任务启动成功!"),
    JOB_STARTUP_FAIL(2,"任务启动失败!"),

    JOB_PAUSE_SUCCESS(3,"任务暂停成功!"),
    JOB_PAUSE_FAIL(4,"任务暂停失败!"),

    JOB_DELETE_SUCCESS(5,"任务删除成功!"),
    JOB_DELETE_FAIL(6,"任务删除失败!"),

    JOB_CREATE_SUCCESS(7,"任务创建成功!"),
    JOB_CREATE_FAIL(8,"任务创建失败!"),

    JOB_REMOVE_SUCCESS(9,"任务移除成功!"),
    JOB_REMOVE_FAIL(10,"任务移除失败!"),



    ;

    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    SchedulerReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
