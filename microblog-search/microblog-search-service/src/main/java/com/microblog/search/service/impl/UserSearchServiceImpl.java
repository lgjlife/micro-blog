package com.microblog.search.service.impl;

import com.microblog.search.service.UserSearchService;
import com.microblog.search.service.elasticsearch.ElasticsearchHandler;
import com.microblog.search.service.elasticsearch.SearchConfig;
import com.microblog.search.service.feign.user.UserFeignService;
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

    @Autowired
    private UserFeignService userFeignService;


    @Override
    public  List<User>  queryUser(String queryString) {

        String[]  types = {"user"};

        SearchConfig searchConfig =  SearchConfig.builder()
                .queryString(queryString)
                .highlightField("nickName")
                .feilds(new String[]{"*"})
                .types(new String[]{"user"})
                .clazz(User.class).build();

        log.info("SearchConfig = " + searchConfig);
        List<User> searchUsers = elasticsearchHandler.query().search(searchConfig);
        if((searchUsers == null ) || (searchUsers.isEmpty())){
            return new ArrayList<>();
        }
        log.info("搜索结果:"+searchUsers);
        List<User> users = new ArrayList<>();
        for(User u:searchUsers){
            User user =  userFeignService.queryUserInfo(u.getUserId());
            if(user!=null){
                user.setNickName(u.getNickName());
                users.add(user);
            }
        }
        return users;
    }
}
