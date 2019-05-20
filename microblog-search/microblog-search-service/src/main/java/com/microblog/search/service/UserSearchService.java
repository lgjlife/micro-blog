package com.microblog.search.service;

import com.microblog.user.dao.model.User;

import java.util.List;

public interface UserSearchService {

    List<User> queryUser(String queryString);
}
