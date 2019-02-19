package com.cloud.microblog.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtils {

    public static void set(Object key, Object value){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(key,value);
    }

    /**
     *功能描述 
     * @author lgj
     * @Description    超时时间为分钟
     * @date 2/18/19
     * @param: 
     * @return: 
     *
    */
    public static void set(Object key, Object value,long timeout){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(key,value);
        session.setTimeout(timeout*1000*60);
    }


    public static Object get(Object key){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return  session.getAttribute(key);
    }

    public static Object remove(Object key){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return  session.removeAttribute(key);
    }


}
