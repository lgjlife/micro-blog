package com.microblog.blog.web.controller;


import com.microblog.blog.dao.model.BlogComment;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.Result;
import com.microblog.common.result.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/blog/comment")
@RestController
@Api(value="/blog/comment",description = "微博评论控制器")
public class BlogCommentController {


    /**
     *功能描述
     * @author lgj
     * @Description  获取一级评论
     * @date 6/30/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @ApiOperation(value = "/query/1",httpMethod = "GET",notes="获取一级评论")
    @GetMapping("/query/1")
    public BaseResult queryOneLevelComment(@RequestParam("blogId") Long blogId,
                                           @RequestParam("pageStart") Integer pageStart,
                                           @RequestParam("pageCount") Integer pageCount){

        if((blogId == null) || (pageStart == null) || (pageCount == null)){
            return new WebResult(Result.RESULT_FAIL,"请求参数出错");
        }

        log.debug("blogId=[{}],pageStart=[{}],pageCount=[{}]",blogId,pageStart,pageCount);
        return new WebResult(Result.RESULT_SUCCESS,"获取一级评论成功");
    }


    /**
     *功能描述  获取二级评论
     * @author lgj
     * @Description
     * @date 6/30/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @ApiOperation(value = "/query/2",httpMethod = "GET",notes="获取二级评论")
    @GetMapping("/query/2")
    public BaseResult queryTwoLevelComment(@RequestParam("blogId") Long blogId,@RequestParam("pId") Long pId){

        if((blogId == null) || (pId == null)) {
            return new WebResult(Result.RESULT_FAIL, "请求参数出错");
        }

        return new WebResult(Result.RESULT_SUCCESS,"获取二级评论成功");
    }

    /**
     *功能描述  创建评论
     * @author lgj
     * @Description
     * @date 6/30/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @ApiOperation(value = "/",httpMethod = "POST",notes="删除评论")
    @PostMapping
    public BaseResult create(@RequestBody BlogComment comment){

        if(comment == null) {
            return new WebResult(Result.RESULT_FAIL, "请求参数出错");
        }

        log.debug("comment = "+comment);

        return new WebResult(Result.RESULT_SUCCESS,"创建评论成功");
    }

    /**
     *功能描述  删除评论
     * @author lgj
     * @Description
     * @date 6/30/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @ApiOperation(value = "/",httpMethod = "DELETE",notes="删除评论")
    @DeleteMapping
    public BaseResult delete(@RequestParam("commentId") Long commentId){

        if(commentId == null) {
            return new WebResult(Result.RESULT_FAIL, "请求参数出错");
        }
        log.debug("删除评论[{}]",commentId);
        return new WebResult(Result.RESULT_SUCCESS,"删除评论成功");
    }


}
