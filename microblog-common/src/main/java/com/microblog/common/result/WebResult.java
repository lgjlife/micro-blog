package com.microblog.common.result;


import com.microblog.common.code.ReturnCode;
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

    public WebResult(ReturnCode code) {
        super(code.getCode(), code.getMessage());
    }

    public WebResult(ReturnCode code, Object object) {
        super(code.getCode(), code.getMessage(),object);
    }

    public WebResult(Integer code, String message) {
        super(code, message);
    }

    public WebResult(Integer code, String message, Object object) {
        super(code, message, object);
    }
}
