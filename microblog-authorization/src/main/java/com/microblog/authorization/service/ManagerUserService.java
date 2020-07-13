package com.microblog.authorization.service;


import com.microblog.authorization.dao.mapper.ManagerUserMapper;
import com.microblog.authorization.dao.model.ManagerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *功能描述 管理员服务
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Service
public class ManagerUserService {

    @Autowired
    private ManagerUserMapper managerUserMapper;

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    public ManagerUser queryManagerUser(String username){
       return managerUserMapper.selectByUsername(username);
    }

    /**
     * 查询密码加密的盐
     * @param username
     * @return
     */
    public String querySalt(String username){

        return managerUserMapper.selectSalt(username);

    }
}
