package com.cloud.microblog.gateway.auth;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class AuthFilterService {

    @Autowired
    AuthPathResource pathResource;

    /**
     *功能描述
     * @author lgj
     * @Description  判断当前的路径是否需要校验token
     * @date 2/28/19
     * @param:
     * @return:
     *
    */
    public  boolean needFilter(HttpServletRequest request){
        String servletPath =  request.getServletPath();
        log.debug("servletPath={}",servletPath);
        Map<String , AbsAppPath> pathMap = pathResource.getPathMap();
        AntPathMatcher matcher =  new AntPathMatcher();

        Set<String> keys =  pathMap.keySet();

        for(String key:keys){
            List<String> paths = pathMap.get(key).getPaths();
            for(String path:paths){

              //  log.debug("matcher path = {}",path);
                if(matcher.match(servletPath,path)==true){
                    log.debug("拦截{}",servletPath);
                    return true;
                }
            }

        }
        log.debug("不拦截{}",servletPath);
        return  false;
    }
}
