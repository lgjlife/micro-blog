package com.microblog.blog.service.service.impl;

import com.microblog.blog.dao.mapper.CommentLikeMapper;
import com.microblog.blog.dao.model.CommentLike;
import com.microblog.blog.service.exception.BlogException;
import com.microblog.blog.service.service.CommentLikeService;
import com.microblog.blog.service.utils.LikeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Override
    public Long likeHandler(long commentId, long userId, String type) {

        if(LikeType.commentLike.equals(type)){
            if(commentLikeMapper.selectCount(commentId,userId) == 1){
                throw new BlogException("您已经点赞!");
            }

            CommentLike commentLike = new CommentLike();
            commentLike.setCommentId(commentId);
            commentLike.setUserId(userId);
            commentLike.setCreateTime(new Date());
            commentLikeMapper.insert(commentLike);
        }
        else {
            commentLikeMapper.delete(commentId,userId);
        }

        Long result = null;
        if((result = commentLikeMapper.selectCountByCommentId(commentId)) == null){
            return 0L;
        }
        return result;

    }
}
