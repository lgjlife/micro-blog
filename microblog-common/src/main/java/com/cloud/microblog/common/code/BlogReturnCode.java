package com.cloud.microblog.common.code;

/**
 *功能描述
 * @author lgj
 * @Description  后台向前端返回的CODE
 * @date 2/19/19
*/
public enum BlogReturnCode implements  ReturnCode{


    //空参数 0
    ERROR_PARAM(0,"输入参数无效"),



    BLOG_QUERY_SUCCESS(10,"博文查询成功"),
    BLOG_QUERY_FAIL(10,"博文查询失败")
    ;



    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    BlogReturnCode(Integer code, String message) {
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
