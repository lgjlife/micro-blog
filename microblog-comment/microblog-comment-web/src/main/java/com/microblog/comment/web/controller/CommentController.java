package com.microblog.comment.web.controller;


import com.microblog.comment.service.service.CommentService;
import com.microblog.commmet.dao.model.BlogComment;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @PrintUrlAnno
    @PostMapping
    public void create(){

        BlogComment blogComment = new BlogComment();
        blogComment.setContent("asadasdas");
        commentService.create(blogComment);


    }

    @PrintUrlAnno
    @GetMapping("/list")
    public List<BlogComment> get(){
        return commentService.get();
    }
}
