package com.cloud.microblog.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtils {

    private static final Long hour = 60*60*1000L;
    private static final Long timeoutHour = 2*hour;

    public static void set(Object key, Object value){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(key,value);
        session.setTimeout(timeoutHour);
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
