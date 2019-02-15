package com.cloud.frame.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @program: top-parent
 * @description: zuul 过滤器
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-17 16:23
 **/

@Component
public class MyFilter extends ZuulFilter {

    private static  final Logger log = LoggerFactory.getLogger(MyFilter.class);


    /**
     * @description:
     * @param:    PRE_TYPE:   请求路由到具体的微服务之前执行，可以做安全验证，身份，参数等
     *            ROUTE_TYPE: 用于将请求到具体的微服务实例
     *            POST_TYPE:  请求已经被路由到微服务之后执行，用作收集统计信息，指标
     *            ERROR_TYPE: 其他过滤器发生错误时执行
     * @return:
     * @author: Mr.lgj
     * @date: 11/17/18
    */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * @description:
     * @param:
     * @return:   值越小越早i执行过滤器
     * @author: Mr.lgj
     * @date: 11/17/18
    */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @description:
     * @param:
     * @return:    true: 过滤器工作， false : 过滤器不工作
     * @author: Mr.lgj
     * @date: 11/17/18
    */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        log.info("过滤器正在执行， filterType = " + this.filterType());
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if(accessToken == null){
           log.info("accessToken = null");
        }
        log.info("accessToken = " + accessToken);

        return null;
    }
}
