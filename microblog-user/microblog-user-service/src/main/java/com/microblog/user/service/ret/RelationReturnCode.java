package com.microblog.user.service.ret;

import com.microblog.common.code.ReturnCode;

public enum  RelationReturnCode implements ReturnCode {


    //空参数 0
    ERROR_PARAM(0,"输入参数无效"),

    FOLLOW_SUCCESS(10,"关注成功"),
    FOLLOW_FAIL(11,"关注失败"),

    UN_FOLLOW_SUCCESS(12,"取消关注成功"),
    UN_FOLLOW_FAIL(13,"取消关注失败"),

    REMOVE_FOLLOWER_SUCCESS(14,"移除粉丝成功"),
    REMOVE_FOLLOWER_FAIL(15,"移除粉丝失败"),

    LIST_FOLLOWER_SUCCESS(16,"获取粉丝列表成功"),
    LIST_FOLLOWER_FAIL(17,"获取粉丝列表失败"),

    LIST_FOLLOWEE_SUCCESS(18,"获取关注者成功"),
    LIST_FOLLOWEE_FAIL(19,"获取关注者失败"),
    ;



    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    RelationReturnCode(Integer code, String message) {
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
