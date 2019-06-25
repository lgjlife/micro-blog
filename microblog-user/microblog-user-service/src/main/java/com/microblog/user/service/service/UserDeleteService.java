package com.microblog.user.service.service;

import com.microblog.user.dao.model.UserDelete;

import java.util.List;

public interface UserDeleteService {

    List<UserDelete> query();
    void delete(List<Long>  longs);

}
