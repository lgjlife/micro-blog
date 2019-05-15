package com.microblog.points.service.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *功能描述 
 * @author lgj
 * @Description 幂等型检测类
 * @date 5/15/19
*/
@Data
@Slf4j
@Component
public class IdempotencyHandlerImpl implements IdempotencyHandler{

    private RedisTemplate redisTemplate;
    private RedissonClient redissonClient;


    /**
     *功能描述
     * @author lgj
     * @Description  检测消息幂等性
     * @date 5/15/19
     * @param:　
     * @return:　
     * 　　　　　　true: 消息未被消费过
     * 　　　　　　false:　消息已经被消费过
     *
    */
    @Override
    public boolean checkConsumption(long id,long time,TimeUnit unit){

        RLock lock = redissonClient.getLock("lock:idempotency:checkConsumption");
        String value = null;
        try{
            lock.lock(20, TimeUnit.SECONDS);
            value = (String)redisTemplate.opsForValue().get(id);
            if(value == null){
                //消息未消费过
                redisTemplate.opsForValue().set(id,"",time,unit);
                return true;
            }
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
        finally{
            lock.unlock();
        }
        return false;
    }


    /**
     *功能描述 
     * @author lgj
     * @Description   checkIdempotency-->消息未消费--->消费消息-->消息消费失败--->reDelete 删除
     * @date 5/15/19
     * @param: 
     * @return: 
     *
    */
    @Override
    public void reDelete(long messageId) {
        redisTemplate.delete(messageId);
    }


}
