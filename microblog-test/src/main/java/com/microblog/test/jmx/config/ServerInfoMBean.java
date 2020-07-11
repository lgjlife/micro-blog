package com.microblog.test.jmx.config;
/**
 *功能描述 
 * @author lgj
 * @Description 必须以MBean结尾　
 * @date 　
*/
public interface ServerInfoMBean {

    int getInfo();
    void setInfo(int count);
}
