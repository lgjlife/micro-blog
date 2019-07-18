package com.microblog.comment.service.service;

import com.microblog.comment.service.dto.CommentDto;
import com.microblog.commment.dao.model.BlogComment;

import java.util.List;

public interface CommentService {

    void create(BlogComment comment);
    List<BlogComment> get();

    public List<CommentDto>  getComments(long blogId, int page, int pageCount);
    Integer createComment(BlogComment comment);
}
