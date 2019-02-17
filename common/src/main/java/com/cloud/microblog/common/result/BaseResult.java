package com.cloud.microblog.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;
    //返回代码
    private Integer  code;

    //返回消息
    private String message;

    //返回对象
    private  Object object;

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }


}
