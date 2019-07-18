package com.microblog.comment.web.controller;


import com.microblog.comment.service.dto.CommentDto;
import com.microblog.comment.service.service.CommentService;
import com.microblog.commment.dao.model.BlogComment;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



   /* @PrintUrlAnno
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
    }*/

    @PrintUrlAnno
    @GetMapping("/list")
    public BaseResult getComments(@RequestParam("blogId") long blogId,
                                  @RequestParam("page")  int page,
                                  @RequestParam("pageCount") int pageCount){

        List<CommentDto> commentDtos = commentService.getComments(blogId,page,pageCount);
        return new WebResult(WebResult.RESULT_SUCCESS,"评论获取成功",commentDtos);

    }

    @PrintUrlAnno
    @PostMapping("/create")
    public  BaseResult createComment(@RequestBody  BlogComment comment){

       Integer result =  commentService.createComment(comment);
       if(result == null){
           return new WebResult(WebResult.RESULT_FAIL,"评论失败");
       }
        return new WebResult(WebResult.RESULT_SUCCESS,"评论成功");

    }


}
