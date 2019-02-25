package com.cloud.microblog.gateway.controller.blog;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.code.BlogReturnCode;
import com.cloud.microblog.common.code.ReturnCode;
import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.result.WebResult;
import com.cloud.microblog.gateway.dto.BlogInfoDto;
import com.cloud.microblog.gateway.service.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description  微博
 * @date 2/22/19
*/
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    BlogService blogService;

   /**
    *功能描述
    * @author lgj
    * @Description  获取微博列表
    * @date 2/22/19
    * @param:
    * @return:
    *
   */
    @PrintUrlAnno
    @PostMapping("/list")
    public BaseResult queryBlog(@RequestBody Map<String,Object>  map){

        String type = (String) map.get("type");
        int start = (Integer) map.get("start");
        int limit= (Integer)map.get("limit");

        log.debug(map.values().toString());

         List<BlogInfoDto> blogInfoDtos = blogService.queryBlog(type,start,limit);

         BaseResult result = new WebResult(BlogReturnCode.BLOG_QUERY_SUCCESS,blogInfoDtos);

         return result;
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
    public BaseResult upLoadHeaderImg(HttpServletRequest request, HttpServletResponse response) {


        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        ReturnCode returnCode = blogService.submit(multiRequest);
        BaseResult result = new WebResult(returnCode);
        return new WebResult(UserReturnCode.HEADER_FILE_NULL);
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
    public BaseResult collect(long blogId){
        ReturnCode returnCode = blogService.collect(blogId);
        BaseResult result = new WebResult(returnCode);
        return result;
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
    public BaseResult repost(long blogId,String content){

        ReturnCode returnCode = blogService.repost(blogId, content);
        BaseResult result = new WebResult(returnCode);
        return result;

    }

    /**
     *功能描述
     * @author lgj
     * @Description   评论
     * @date 2/22/19
     * @param:
     * @return:
     *
    */
    @PrintUrlAnno
    @PostMapping("/comment")
    public BaseResult comment(long blogId,String content){

        ReturnCode returnCode = blogService.collect(blogId);
        BaseResult result = new WebResult(returnCode);
        return result;

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
    public BaseResult like(long blogId){
        ReturnCode returnCode = blogService.collect(blogId);
        BaseResult result = new WebResult(returnCode);
        return result;

    }




}
