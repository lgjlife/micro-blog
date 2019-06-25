package com.microblog.search.service.impl;

import com.microblog.search.service.UserSearchService;
import com.microblog.search.service.dto.SearchUserDto;
import com.microblog.search.service.elasticsearch.ElasticsearchHandler;
import com.microblog.search.service.elasticsearch.SearchConfig;
import com.microblog.user.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Autowired
    private ElasticsearchHandler elasticsearchHandler;

    @Override
    public List<SearchUserDto>  queryUser(String queryString) {

        String[]  types = {"user"};

        SearchConfig searchConfig =  SearchConfig.builder()
                .queryString(queryString)
                .highlightField("nickName")
                .types(types)
                .clazz(User.class).build();

        log.info("SearchConfig = " + searchConfig);
        List<User> users = elasticsearchHandler.query().search(searchConfig);
        List<SearchUserDto> searchUserDtos = new ArrayList<>(users.size());

        for(int i = 0; i< users.size(); i++){
            User user = users.get(i);
            SearchUserDto searchUserDto = SearchUserDto.builder()
                    .id(user.getUserId())
                    .nickName(user.getNickName())
                    .headerImgUrl(user.getHeaderUrl())
                    .build();

            searchUserDtos.add(searchUserDto);
        }
        log.info("搜索结果:[{}],users = {}",searchUserDtos.size(),  searchUserDtos);

        return searchUserDtos;
    }
}
