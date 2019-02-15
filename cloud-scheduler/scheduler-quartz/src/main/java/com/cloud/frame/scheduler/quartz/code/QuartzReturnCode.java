package com.cloud.frame.scheduler.quartz.code;

/**
 * @program: shiro
 * @description: 用户相关枚举类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 01:06
 **/
public enum QuartzReturnCode implements  ReturnCode{


    //空参数 0
    NULL_POINTER(0,"输入参数无效"),

    //验证码 100 - 109
    QUARTZ_ADD_SUCCESS(100,"QUARTZ添加任务成功"),
    QUARTZ_ADD_FAIL(100,"QUARTZ添加任务失败"),
    QUARTZ_TRIGGER_SUCCESS(100,"QUARTZ触发任务成功"),
    QUARTZ_TRIGGER_FAIL(100,"QUARTZ触发任务失败"),
    QUARTZ_PAUSE_SUCCESS(100,"QUARTZ暂停任务成功"),
    QUARTZ_PAUSE_FAIL(100,"QUARTZ暂停任务失败"),
    QUARTZ_RESUME_SUCCESS(100,"QUARTZ启动任务成功"),
    QUARTZ_RESUME_FAIL(100,"QUARTZ启动任务失败"),
    QUARTZ_DELETE_SUCCESS(100,"QUARTZ删除任务成功"),
    QUARTZ_DELETE_FAIL(100,"QUARTZ删除任务失败"),

    ;

    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    QuartzReturnCode(Integer code, String message) {
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
