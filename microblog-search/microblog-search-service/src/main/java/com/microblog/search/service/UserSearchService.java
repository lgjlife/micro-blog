package com.microblog.search.service;

import com.microblog.search.service.dto.SearchUserDto;

import java.util.List;

public interface UserSearchService {

    List<SearchUserDto> queryUser(String queryString);
}
