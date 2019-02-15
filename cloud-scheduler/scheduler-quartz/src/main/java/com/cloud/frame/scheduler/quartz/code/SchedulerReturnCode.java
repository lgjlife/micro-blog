package com.cloud.frame.scheduler.quartz.code;

/**
 * @program: shiro
 * @description: 用户相关枚举类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 01:06
 **/
public enum SchedulerReturnCode implements  ReturnCode{

    REQUEST_OK(100,"请求成功"),
    REQUEST_FAIL(101,"请求失败");


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
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }




}
