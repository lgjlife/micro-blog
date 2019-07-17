package com.microblog.comment.service.service.impl;

import com.microblog.comment.service.service.CommentService;
import com.microblog.commmet.dao.mapper.BlogCommentMapper;
import com.microblog.commmet.dao.model.BlogComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private  BlogCommentMapper commentMapper;

    @Override
    public void create(BlogComment comment) {
        Integer result = commentMapper.insert(comment);

        log.info("创建结果:"+result);
    }

    @Override
    public List<BlogComment> get() {


        return  commentMapper.selectAll();
    }
}
