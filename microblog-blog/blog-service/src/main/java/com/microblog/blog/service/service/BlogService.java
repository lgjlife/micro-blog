package com.microblog.blog.service.service;


import com.microblog.blog.service.dto.BlogInfoDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 *功能描述 
 * @author lgj
 * @Description
 * @date 4/4/19
*/
public interface BlogService {

    List<BlogInfoDto>  queryBlog(String type, long userId,int page, int count);
    void submit(MultipartHttpServletRequest multiRequest);
    long collect(long blogId,long userId);
    long repost(long blogId,long userId, String content);
    long comment(long blogId,long userId, String content);
    long like(long blogId,long userId,String type);


}
