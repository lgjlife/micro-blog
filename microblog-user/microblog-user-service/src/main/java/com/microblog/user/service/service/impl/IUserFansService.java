package com.microblog.user.service.service.impl;

import com.microblog.user.dao.mapper.UserMapper;
import com.microblog.user.dao.model.Concerns;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.service.UserFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 *功能描述 
 * @author lgj
 * @Description  用户关注相关操作
 * @date 5/29/19
*/
@Service
public class IUserFansService implements UserFansService {

    @Autowired
    private FansMapper fansMapper;

    @Autowired
    private ConcernsMapper concernsMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    RedisTemplate redisTemplate;




    @Override
    public void concerns(long userId, long concernId) {

        Concerns concerns = new Concerns();
        concerns.setUserId(userId);
        concerns.setConcernId(concernId);
        concernsMapper.insert(concerns);

        User user = userMapper.selectByPrimaryKey(concernId);
        redisTemplate.opsForHash().put("Concerns"+":"+userId,concernId,user);

        User user1 = userMapper.selectByPrimaryKey(userId);
        redisTemplate.opsForHash().put("Fans"+":"+concernId,userId,user);
    }

    @Override
    public void unConcerns(long userId, long concernId) {
        concernsMapper.delete(userId,concernId);
        redisTemplate.opsForHash().delete("Concerns"+":"+userId,concernId);
    }

    @Override
    public List<User> queryConcerns(long userId) {

        Set<Long> keys = redisTemplate.opsForHash().keys("Concerns"+":"+userId);

        List<User> users =  redisTemplate.opsForHash().multiGet("Concerns"+":"+userId,keys);

        return users;
    }

    @Override
    public List<User> queryFans(long userId) {

        Set<Long> keys = redisTemplate.opsForHash().keys("Fans"+":"+userId);

        List<User> users =  redisTemplate.opsForHash().multiGet("Fans"+":"+userId,keys);

        return users;
    }
}
