package com.microblog.user.service.service;

import com.microblog.user.dao.model.User;

import java.util.List;

public interface UserFansService {

    /**
     *功能描述
     * @author lgj
     * @Description 关注
     * @date 5/29/19
     * @param:
     * @return:
     *
    */
    void concerns(long userId,long concernId);
    /**
     *功能描述
     * @author lgj
     * @Description 取消关注
     * @date 5/29/19
     * @param:
     * @return:
     *
    */
    void unConcerns(long userId,long concernId);

    /**
     *功能描述
     * @author lgj
     * @Description  查询关注列表
     * @date 5/29/19
     * @param:
     * @return:
     *
    */
    List<User> queryConcerns(long userId);

    /**
     *功能描述
     * @author lgj
     * @Description  查询粉丝列表
     * @date 5/29/19
     * @param:
     * @return:
     *
    */

    List<User> queryFans(long userId);


}
