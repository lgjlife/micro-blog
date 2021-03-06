package com.microblog.blog.web.controller;


import com.microblog.blog.dao.dto.BlogInfoDto;
import com.microblog.blog.service.service.BlogService;
import com.microblog.blog.service.service.CommentLikeService;
import com.microblog.blog.service.utils.LikeType;
import com.microblog.blog.service.utils.UserUtil;
import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.code.BlogReturnCode;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.Result;
import com.microblog.common.result.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description  微博 controller
 * @date 2/22/19
*/
@Slf4j
@RestController
@RequestMapping("/blog")
@Api(value="/blog",description="博客操作相关控制器")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CommentLikeService commentLikeService;
   /**
    *功能描述
    * @author lgj
    * @Description  获取微博列表
    * @date 2/22/19
    * @param:  type: @See BlogType
    *          page: 页数
    *          count: 数量
    * @return:
    *
   */
    @PrintUrlAnno
    @PostMapping("/list")
    @ApiOperation(value = "/list",httpMethod = "POST",notes="查询博客")
    @ApiParam(name="map")
    public BaseResult queryBlog(@RequestBody Map<String,Object>  map){

        String type = (String) map.get("type");
        int page = (Integer) map.get("page");
        int count= (Integer)map.get("count");

        log.debug("queryBlog....");
       // log.debug("userId = " + request.getHeader("userId"));
        List<BlogInfoDto> blogInfoDtos = blogService.queryBlog(type,1,page,count);

        BaseResult result = new WebResult(BlogReturnCode.BLOG_QUERY_SUCCESS.getCode(),
                BlogReturnCode.BLOG_QUERY_SUCCESS.getMessage(),blogInfoDtos);
        return result;
    }
    @PrintUrlAnno
    @GetMapping("/query/1")
    public  BlogInfoDto queryBlog(@RequestParam("blogId") Long blogId, @RequestParam("userId") Long userId){
        BlogInfoDto blogInfoDto =  blogService.queryBlog(blogId,userId);
        return  blogInfoDto;
    }


    /**
     *功能描述
     * @author lgj
     * @Description
     * @date 2/22/19
     * @param:  发布微博
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/submit")
    @ApiOperation(value = "/submit",httpMethod = "POST",notes="上传头像")
    public BaseResult upLoadHeaderImg(HttpServletRequest request) {

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
         blogService.submit(multiRequest);
        return new WebResult(Result.RESULT_SUCCESS,"l微博发布成功");
    }

    /**
     *功能描述
     * @author lgj
     * @Description
     * @date 2/22/19
     * @param:  收藏
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/collect")
    @ApiOperation(value = "/collect",httpMethod = "POST",notes="收藏博客")
    @ApiParam(name="blogId")
    public BaseResult collect(@RequestParam("blogId")Long blogId){

        Long userId = UserUtil.getUserId(request);
        if((userId == null) || (blogId == null)){
            return new WebResult(Result.RESULT_FAIL,"操作失败:请求参数错误!");
        }
        long result  = blogService.collect(blogId,userId);
        return new WebResult(Result.RESULT_SUCCESS,"操作成功!",result);
    }
    /**
     *功能描述
     * @author lgj
     * @Description
     * @date 2/22/19
     * @param:  转发
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/repost")
    @ApiOperation(value = "/repost",httpMethod = "POST",notes="转发博客")
    public BaseResult repost(Long blogId,String content){

        Long userId = UserUtil.getUserId(request);
        if((userId == null) || (blogId == null)){
            return new WebResult(Result.RESULT_FAIL,"操作失败:请求参数错误!");
        }

        long result = blogService.repost(blogId,userId, content);
        return new WebResult(Result.RESULT_SUCCESS,"操作成功!",result);

    }



    /**
     *功能描述
     * @author lgj
     * @Description  点赞
     * @date 2/22/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/like")
    @ApiOperation(value = "/like",httpMethod = "POST",notes="点赞")
    public BaseResult like(@RequestParam("id") Long id,@RequestParam("type") String type){
        Long userId = UserUtil.getUserId(request);

        if((userId == null) || (id == null) ||  (type == null)){
            return new WebResult(Result.RESULT_FAIL,"操作失败:请求参数错误!");
        }
        log.debug("userId:[{}],id:[{}] type:[{}] ",userId,id,type );

        if((LikeType.blogLike.equals(type))||(LikeType.blogUnlike.equals(type))){
            long curLikeNum = blogService.like(id,userId,type);
            return new WebResult(Result.RESULT_SUCCESS,"操作成功!",curLikeNum);
        }
        else  if((LikeType.commentLike.equals(type))||(LikeType.commentUnlike.equals(type))){
            long curLikeNum = commentLikeService.likeHandler(id,userId,type);
            return new WebResult(Result.RESULT_SUCCESS,"操作成功!",curLikeNum);
        }

        else {
            return new WebResult(Result.RESULT_FAIL,"操作失败:请求参数错误!");
        }
    }
}
