package com.cloud.microblog.gateway.service.blog.service;


import com.cloud.microblog.common.code.ReturnCode;
import com.cloud.microblog.gateway.dto.BlogInfoDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BlogService {

    List<BlogInfoDto>  queryBlog(String type,int start , int end);

    ReturnCode submit(MultipartHttpServletRequest multiRequest);


    ReturnCode collect(long blogId);
    ReturnCode repost(long blogId,String content);
    ReturnCode comment(long blogId,String content);
    ReturnCode like(long blogId);

}
