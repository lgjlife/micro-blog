package com.cloud.microblog.gateway.service.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Slf4j
@Component
public class ShiroSessionDao extends CachingSessionDAO {



    //用作key的前缀，key = keyPrefix + session_id
    private String keyPrefix = "SHIRO_REDIS_SESSION:";
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

        log.debug("doUpdate,session({})",session.getId());
        sessionPrint(session);
        Collection<Object> keys =  session.getAttributeKeys();
        log.debug("keys = " + keys);

        try{
         //   redisTemplate.opsForValue().set(getKey(session.getId().toString()),session);
        }
        catch(Exception ex){

            log.debug("Exception:{}--{}",ex.getCause(),ex.getMessage());
        }

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

        log.debug("doDelete,session({})",session.getId());

        try{
          //  redisTemplate.delete(getKey(session.getId().toString()));
        }
        catch(Exception ex){
            log.debug("Exception:{}--{}",ex.getCause(),ex.getMessage());
        }

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
        log.debug("doCreate,session({})",session.getId());
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        log.info("session.toString"+session.toString());
        log.info(""+session.getHost());
        log.info(session.getId()+"");

        try{
          //  redisTemplate.opsForValue().set(getKey(session.getId().toString()),session);
        }
        catch(Exception ex){
            log.debug("Exception:{}--{}",ex.getCause(),ex.getMessage());
        }
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

        log.debug("doCreate,session({})",serializable.toString());
        Session session = null;

        try{
        //    session =  (Session)redisTemplate.opsForValue().get(getKey(serializable.toString()));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        if(session == null){
            log.debug("session is null!");
        }
        return session;
    }

    private String getKey(String sessionId){
        return keyPrefix + sessionId;
    }

    private void sessionPrint(Session session){

        if(session  == null){
            log.debug("session is null!");
        }

        log.debug("LastAccessTime={},AttributeKeys = {},Host={},Id={},StartTimestamp={},Timeout={}",
                session.getLastAccessTime(),
                session.getAttributeKeys(),
                session.getHost(),
                session.getId(),
                session.getStartTimestamp(),
                session.getTimeout());
    }
}
