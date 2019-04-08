package com.microblog.gateway.auth;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *功能描述 
 * @author lgj
 * @Description    配置哪些路径需要验证Token
 * @date 2/28/19
*/
@Component
public class AuthPathResource  implements ApplicationContextAware {

    @Autowired
    ChatAppPath chatAppPath;

    @Autowired
    UserAppPath userAppPath;

    @Autowired
    BlogAppPath blogAppPath;


    private Map<String , AbsAppPath>  pathMap = new HashMap();


    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        pathMap.put(chatAppPath.getName(),chatAppPath);
        pathMap.put(userAppPath.getName(),userAppPath);
        pathMap.put(blogAppPath.getName(),blogAppPath);
    }

    public Map<String, AbsAppPath> getPathMap() {
        return pathMap;
    }
}
