package com.microblog.user.service.service;

import com.microblog.user.dao.model.User;
import com.microblog.user.service.ret.RelationReturnCode;

import java.util.List;

public interface UserRelationService {

    /**
     *功能描述
     * @author lgj
     * @Description  关注操作,当前用户关注folleeId
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    public RelationReturnCode follow(long userId, long folleeId);

    /**
     *功能描述
     * @author lgj
     * @Description 取消关注操作,当前用户取消关注folleeId
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    public RelationReturnCode unfollow(long userId,long folleeId);


    /**
     *功能描述
     * @author lgj
     * @Description  移除关注者
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    public RelationReturnCode removeFollower(long userId,long follerId);

    /**
     *功能描述
     * @author lgj
     * @Description  获取粉丝列表
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    public List<User> listFollower(long userId);

    /**
     *功能描述
     * @author lgj
     * @Description  移除关注者
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    public List<User> listFollowee(long userId);
}
