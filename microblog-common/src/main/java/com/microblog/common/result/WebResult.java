package com.microblog.common.result;


import lombok.Data;

/**
 *功能描述
 * @author lgj
 * @Description 后台返回前端的包装类。
 * @date 6/7/19
*/
@Data
public class WebResult extends BaseResult{

    private static final long serialVersionUID = 1L;
    public static  Integer RESULT_FAIL = 0;
    public static  Integer RESULT_SUCCESS = 1;

    public WebResult(Integer code, String message) {
        super(code, message);
    }

    public WebResult(Integer code, String message, Object object) {
        super(code, message, object);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
