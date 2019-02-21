package com.cloud.microblog.gateway.service.blog.service;


import com.cloud.microblog.gateway.dto.BlogInfoDto;

import java.util.List;

public interface BlogService {

    List<BlogInfoDto>  queryBlog(String type,int start , int end);

}
