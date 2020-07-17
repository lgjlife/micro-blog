package com.microblog.authorization.config.handler;

import com.microblog.util.result.ResponseCode;
import com.microblog.util.result.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;


@Slf4j
public class ExceptioHandler implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception ex) throws Exception {


        WebResult responseDto = null;


        responseDto = new WebResult(ResponseCode.FAIL.getCode(),ex.getMessage());

        log.info("responseDto = " + responseDto);
        ResponseEntity<WebResult> responseEntity
                = new ResponseEntity<WebResult>(responseDto,HttpStatus.valueOf(200));

        return responseEntity;



//        log.info("发生异常"+ex.getCause());
//        //内部会将用户抛出的异常转化为InternalAuthenticationServiceException，需要获取原始的异常ex.getCause()
//        //如果是oauth2抛出的异常直接是InvalidGrantException
//
//        //处理内部的异常
//        if(ex instanceof InvalidGrantException){
//            responseDto = new ServerResponseDto(1,"不正确的凭证");
//
//            log.info("responseDto = " + responseDto);
//            ResponseEntity<ServerResponseDto> responseEntity
//                    = new ResponseEntity<ServerResponseDto>(responseDto,HttpStatus.valueOf(200));
//
//            return responseEntity;
//        }
//
//        //InternalAuthenticationServiceException
//
//        //处理用户抛出的异常
//        Throwable exception = ex.getCause();
//        if(exception instanceof DisabledAccountException){
//            responseDto = new ServerResponseDto(1,"帐号被禁用");
//        }
//        else if(exception instanceof ExcessiveAttemptsException){
//            responseDto = new ServerResponseDto(1,"认证次数超过限制");
//        }
//        else if(exception instanceof ExpiredCredentialsException){
//            responseDto = new ServerResponseDto(1,"凭证过期");
//        }
//        else if(exception instanceof IncorrectCredentialsException){
//            responseDto = new ServerResponseDto(1,"不正确的凭证");
//        }
//        else if(exception instanceof LockedAccountException){
//            responseDto = new ServerResponseDto(1,"账号被锁定");
//        }
//        else if(exception instanceof UnknownAccountException){
//            responseDto = new ServerResponseDto(1,"未知的账号");
//        }
//
//
//        log.info("responseDto = " + responseDto);
//        ResponseEntity<ServerResponseDto> responseEntity
//                = new ResponseEntity<ServerResponseDto>(responseDto,HttpStatus.valueOf(200));
//
//        return responseEntity;


    }
}
