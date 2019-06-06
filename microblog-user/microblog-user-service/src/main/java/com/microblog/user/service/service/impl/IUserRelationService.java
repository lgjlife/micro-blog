package com.microblog.user.service.service.impl;

import com.microblog.user.dao.mapper.RelationMapper;
import com.microblog.user.dao.mapper.UserMapper;
import com.microblog.user.dao.model.Relation;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.ret.RelationReturnCode;
import com.microblog.user.service.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserRelationService implements UserRelationService {


    @Autowired
    RelationMapper relationMapper;

    @Autowired
    UserMapper userMapper;

    /**
     *功能描述
     * @author lgj
     * @Description  关注操作,当前用户关注folleeId
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @Override
    public RelationReturnCode follow(long userId, long followeeId) {

        Relation relation = new Relation();
        relation.setFollowerid(userId);
        relation.setFolloweeid(followeeId);
        Integer result = relationMapper.insert(relation);
        if(result == null){
            return RelationReturnCode.FOLLOW_FAIL;
        }

        return RelationReturnCode.FOLLOW_SUCCESS;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 取消关注操作,当前用户取消关注folleeId
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @Override
    public RelationReturnCode unfollow(long userId, long followeeId) {
        Integer result =  relationMapper.unfollow(userId,followeeId);
        if(result == null){
            return RelationReturnCode.UN_FOLLOW_FAIL;
        }

        return RelationReturnCode.UN_FOLLOW_SUCCESS;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  移除关注者
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @Override
    public RelationReturnCode removeFollower(long userId, long followerId) {
        Integer result =  relationMapper.unfollow(followerId,userId);
        if(result == null){
            return RelationReturnCode.REMOVE_FOLLOWER_FAIL;
        }

        return RelationReturnCode.REMOVE_FOLLOWER_SUCCESS;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取粉丝列表
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @Override
    public List<User> listFollower(long followerId) {
        List<Long>  usedIds = relationMapper.selectAllByFollowerId(followerId);
        if(usedIds != null){
            List<User> users = userMapper.selectUsersInfo(usedIds);
            return users;
        }
        return null;


    }

    /**
     *功能描述
     * @author lgj
     * @Description  移除关注者
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @Override
    public List<User> listFollowee(long followerId) {
        List<Long>  usedIds = relationMapper.selectAllByFollowerId(followerId);
        if(usedIds != null){
            List<User> users = userMapper.selectUsersInfo(usedIds);
            return users;
        }
        return null;
    }
}
