package com.microblog.common.result;


import com.microblog.common.code.ReturnCode;
import lombok.Data;

@Data
public class WebResult extends BaseResult{

    private static final long serialVersionUID = 1L;

    public WebResult(ReturnCode code) {
        super(code.getCode(), code.getMessage());
    }

    public WebResult(ReturnCode code, Object object) {
        super(code.getCode(), code.getMessage(),object);
    }



}
