package com.clolud.microblog.search.dao;

import com.cloud.microblog.user.dao.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,String> {

}
