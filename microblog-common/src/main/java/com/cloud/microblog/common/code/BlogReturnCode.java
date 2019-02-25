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
    BLOG_QUERY_FAIL(11,"博文查询失败"),
    BLOG_SUBMIT_SUCCESS(12,"博文发布成功"),
    BLOG_SUBMIT_FAIL(13,"博文发布失败"),
    BLOG_COLLECT_SUCCESS(14,"博文收藏成功"),
    BLOG_COLLECT_FAIL(15,"博文收藏失败"),
    BLOG_REPOST_SUCCESS(16,"博文转发成功"),
    BLOG_REPOST_FAIL(17,"博文转发失败"),
    BLOG_LIKE_SUCCESS(18,"博文点赞成功"),
    BLOG_LIKE_FAIL(19,"博文点赞失败")
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
