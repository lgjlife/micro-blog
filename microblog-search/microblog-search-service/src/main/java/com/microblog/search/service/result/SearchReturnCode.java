package com.microblog.search.service.result;

import com.microblog.common.code.ReturnCode;

public enum SearchReturnCode implements ReturnCode {


    SEARCH_NULL_PARAM(10,"请求参数为空！"),
    SEARCH_SUCCESS(20,"搜索成功！"),
    SEARCH_FAIL(20,"搜索失败！"),
    ;


    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    SearchReturnCode(Integer code, String message) {
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
