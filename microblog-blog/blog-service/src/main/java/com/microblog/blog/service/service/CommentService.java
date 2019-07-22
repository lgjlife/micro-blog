package com.microblog.blog.service.service;


import com.microblog.blog.dao.model.BlogComment;
import com.microblog.blog.service.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void create(BlogComment comment);
    List<BlogComment> get();

    public List<CommentDto>  getComments(long blogId, long userId,int page, int pageCount);
    Integer createComment(BlogComment comment);

    Integer deleteComment(Long commentId);
}
