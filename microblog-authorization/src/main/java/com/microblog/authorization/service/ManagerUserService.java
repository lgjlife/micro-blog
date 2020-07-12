package com.microblog.authorization.service;


import com.microblog.authorization.dao.mapper.ManagerUserMapper;
import com.microblog.authorization.dao.model.ManagerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerUserService {

    @Autowired
    private ManagerUserMapper managerUserMapper;

    public ManagerUser queryManagerUser(String username){
       return managerUserMapper.selectByUsername(username);
    }

    public String querySalt(String username){

        return managerUserMapper.selectSalt(username);

    }
}
