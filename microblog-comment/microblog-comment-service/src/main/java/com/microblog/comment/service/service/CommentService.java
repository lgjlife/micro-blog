package com.microblog.comment.service.service;

import com.microblog.commmet.dao.model.BlogComment;

import java.util.List;

public interface CommentService {

    void create(BlogComment comment);
    List<BlogComment> get();
}
