package com.clolud.microblog.search.dao;

import com.cloud.microblog.blog.dao.model.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<Blog,String> {

}
