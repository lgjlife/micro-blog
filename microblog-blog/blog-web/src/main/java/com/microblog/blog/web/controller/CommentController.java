package com.microblog.blog.web.controller;


import com.microblog.blog.dao.model.BlogComment;
import com.microblog.blog.service.dto.CommentDto;
import com.microblog.blog.service.service.CommentService;
import com.microblog.blog.service.utils.UserUtil;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.Result;
import com.microblog.common.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/blog/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HttpServletRequest request;


    @PrintUrlAnno
    @GetMapping("/list")
    public BaseResult getComments(@RequestParam("blogId") long blogId,
                                  @RequestParam("page")  int page,
                                  @RequestParam("pageCount") int pageCount){

        Long userId = UserUtil.getUserId(request);

        if((userId == null)){
            return new WebResult(Result.RESULT_FAIL,"操作失败:用户未登录!");
        }


        List<CommentDto> commentDtos = commentService.getComments(blogId,userId,page,pageCount);
        return new WebResult(WebResult.RESULT_SUCCESS,"评论获取成功",commentDtos);

    }

    @PrintUrlAnno
    @PostMapping("/create")
    public  BaseResult createComment(@RequestBody BlogComment comment){

       Integer result =  commentService.createComment(comment);
       if(result == null){
           return new WebResult(WebResult.RESULT_FAIL,"评论失败");
       }
        return new WebResult(WebResult.RESULT_SUCCESS,"评论成功");

    }

    @PrintUrlAnno
    @DeleteMapping("/delete")
    public  BaseResult deleteComment(@RequestParam("commentId") Long commentId ){

        if(commentId == null){
            return new WebResult(WebResult.RESULT_FAIL,"参数错误，删除评论失败");
        }

        Integer result =  commentService.deleteComment(commentId);
        if((result == null)||(result == 0)){
            return new WebResult(WebResult.RESULT_FAIL,"删除评论失败");
        }
        return new WebResult(WebResult.RESULT_SUCCESS,"删除评论成功");

    }




}
