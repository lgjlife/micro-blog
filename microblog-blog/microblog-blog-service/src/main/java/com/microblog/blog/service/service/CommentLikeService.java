package com.microblog.blog.service.service;

public interface CommentLikeService {

    Long likeHandler(long commentId,long userId,String type);
}
