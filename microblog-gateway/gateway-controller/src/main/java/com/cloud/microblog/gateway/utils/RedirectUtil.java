package com.cloud.microblog.gateway.utils;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *功能描述
 * @author lgj
 * @Description  重定向工具类
 * @date 2/27/19
*/
@Slf4j
public class RedirectUtil {

    /**
     *功能描述
     * @author lgj
     * @Description  重定向
     * @date 2/27/19
     * @param:
     * @return:
     *
    */
    public  static void  redirect(RequestContext ctx, String redirectUrl){

        try{
            //如果是Ajax请求
            if("XMLHttpRequest".equals(ctx.getRequest().getHeader("X-Requested-With"))){
                log.debug("ajax redirect");
                sendRedirect(ctx.getResponse(),redirectUrl);
            }
            //如果是浏览器地址栏请求
            else {

                log.debug("normal redirect ");
                ctx.getResponse().sendRedirect(redirectUrl);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }


    /**
     *功能描述 
     * @author lgj
     * @Description   Ajax请求处理
     * @date 2/27/19
     * @param: 
     * @return: 
     *
    */
    private static void sendRedirect(HttpServletResponse response, String redirectUrl){
        try {
            //设置跳转地址
            response.setHeader("redirectUrl", redirectUrl);
            //设置跳转使能
            response.setHeader("enableRedirect","true");
            response.flushBuffer();
        } catch (IOException ex) {
            log.error("Could not redirect to: " + redirectUrl, ex);
        }
    }
}
