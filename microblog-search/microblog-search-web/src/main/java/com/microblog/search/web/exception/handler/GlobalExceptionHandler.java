package com.microblog.search.web.exception.handler;

import com.microblog.common.result.BaseResult;
import com.microblog.common.result.Result;
import com.microblog.common.result.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public abstract  class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResult globalException(HttpServletResponse response,Exception ex){
        log.info("GlobalExceptionHandler...");
        log.info("错误代码："  + response.getStatus());
        BaseResult result = new WebResult(Result.RESULT_FAIL,ex.getMessage());
        return result;
    }

}

