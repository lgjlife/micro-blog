package com.clolud.microblog.search.service;

import com.microblog.user.dao.model.User;

import java.util.List;

public interface UserSearchService {

    List<User> search(int page, int count, String  query);
}
