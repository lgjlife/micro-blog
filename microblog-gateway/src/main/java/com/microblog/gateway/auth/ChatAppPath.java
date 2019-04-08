package com.microblog.gateway.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *功能描述 
 * @author lgj
 * @Description  读取yml 文件中的路径配置，该路径将会验证Token
 * @date 2/28/19
*/
@Slf4j
@Component
@ConfigurationProperties(prefix = "auth.chat")
public class ChatAppPath extends  AbsAppPath{

    public void setPaths(List<String> paths) {
        super.paths = paths;
     //   log.debug("paths len = {}" ,paths.size());
    //    paths.forEach((value)->log.debug("path value = {}",value));
    }

    public void setName(String name) {
        super.name = name;

     //   log.debug("name = {}",name);
    }

    public List<String> getPaths() {
        return paths;
    }

    public String getName() {
        return name;
    }


}
