package com.microblog.user.service.service.impl;

import com.microblog.user.dao.mapper.UserDeleteMapper;
import com.microblog.user.dao.model.UserDelete;
import com.microblog.user.service.service.UserDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserDeleteServiceImpl implements UserDeleteService {


    @Autowired
    UserDeleteMapper userDeleteMapper;


    @Override
    public List<UserDelete> query() {
        return userDeleteMapper.selectAll();
    }

    @Override
    public void delete(List<Long> uids) {
        userDeleteMapper.deleteByUid(uids);
    }
}
