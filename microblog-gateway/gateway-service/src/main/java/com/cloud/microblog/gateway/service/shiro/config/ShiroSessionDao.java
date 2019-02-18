package com.cloud.microblog.gateway.service.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ShiroSessionDao extends CachingSessionDAO {



    //用作key的前缀，key = keyPrefix + session_id
    private String keyPrefix = "SHIRO_REDIS_SESSION:";
    //session 缓存过期时间
    private static  final int cacheTimeMinute = 30;

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * @description:
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 9/1/18
     */
    @Override
    protected void doUpdate(Session session) {

        log.debug("doUpdate 。。。。");
        redisTemplate.opsForValue().set(getKey(session.getId().toString()),session,cacheTimeMinute, TimeUnit.MILLISECONDS);

    }

    /**
     * @description:
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 9/1/18
     */
    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(getKey(session.getId().toString()));

    }

    /**
     * @description:
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 9/1/18
     */
    @Override
    protected Serializable doCreate(Session session) {

        if(session == null){
            log.info("session is null");
        }
        log.info("doCreate");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        log.info("session.toString"+session.toString());
        log.info(""+session.getHost());
        log.info(session.getId()+"");
        redisTemplate.opsForValue().set(getKey(session.getId().toString()),session,cacheTimeMinute,TimeUnit.SECONDS);
        return sessionId;
    }

    /**
     * @description:
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 9/1/18
     */
    @Override
    protected Session doReadSession(Serializable serializable) {

        log.info("doReadSession");
        Session session =  (Session)redisTemplate.opsForValue().get(getKey(serializable.toString()));
        log.info("doReadSession -- serializable.toString()" + serializable.toString());
        if(session == null){
            log.debug("session is null!");
        }
        return session;
    }

    private String getKey(String sessionId){
        return keyPrefix + sessionId;
    }
}
