package com.cloud.microblog.chat.ret;

import com.cloud.microblog.common.code.ReturnCode;

public enum  ChatReturn implements ReturnCode {

    //空参数 0
    ERROR_PARAM(0,"输入参数无效"),
    CHAT_USER_LIST_SUCCESS(10,"私信聊天用户列表获取成功"),
    CHAT_USER_LIST_FAIL(10,"私信聊天用户列表获取失败"),
    ;


    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    ChatReturn(Integer code, String message) {
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
