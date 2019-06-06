package com.microblog.user.web.controller;


import com.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.microblog.common.code.ReturnCode;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.ret.RelationReturnCode;
import com.microblog.user.service.service.UserRelationService;
import com.microblog.user.service.service.UserService;
import com.microblog.user.service.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *功能描述
 * @author lgj
 * @Description  用户关系控制类
 * @date 6/6/19
*/
@RestController
@RequestMapping("/user/relation")
@Api(value = "/user/relation",description = "用户关系")
public class UserRelationController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRelationService userRelationService;

    @Autowired
    UserService userService;


    /**
     *功能描述
     * @author lgj
     * @Description  关注操作,当前用户关注folleeId
     * @date 6/6/19
     * @param:
     * @return:
     *
    */
    @ApiOperation(value = "/follow",notes="关注操作",httpMethod = "POST")
    @ApiParam(name="folleeId",value = "被关注者id")
    @PrintUrlAnno
    @PostMapping("/follow")
    public BaseResult follow(Long folleeId){
        if(folleeId == null){
            return new WebResult(RelationReturnCode.ERROR_PARAM);
        }
        Long currentUserId =  UserUtil.getUserId(request);
        ReturnCode returnCode = userRelationService.follow(currentUserId,folleeId);
        return new WebResult(returnCode);
    }

    /**
     *功能描述
     * @author lgj
     * @Description 取消关注操作,当前用户取消关注folleeId
     * @date 6/6/19
     * @param:
     * @return:
     *
    */
    @ApiOperation(value = "/follow",notes="取消关注操作",httpMethod = "POST")
    @ApiParam(name="folleeId",value = "被关注者id")
    @PrintUrlAnno
    @PostMapping("/unfollow")
    public BaseResult unfollow(Long folleeId){
        if(folleeId == null){
            return new WebResult(RelationReturnCode.ERROR_PARAM);
        }

        Long currentUserId =  UserUtil.getUserId(request);
        ReturnCode returnCode = userRelationService.unfollow(currentUserId,folleeId);
        return new WebResult(returnCode);


    }


    /**
     *功能描述
     * @author lgj
     * @Description  移除关注者
     * @date 6/6/19
     * @param:
     * @return:
     *
    */
    @ApiOperation(value = "/follow",notes="移除关注者操作",httpMethod = "DELETE")
    @ApiParam(name="folleeId",value = "粉丝id")
    @PrintUrlAnno
    @DeleteMapping("/follow")
    public BaseResult removeFollower(Long follerId){

        if(follerId == null){
            return new WebResult(RelationReturnCode.ERROR_PARAM);
        }
        Long currentUserId =  UserUtil.getUserId(request);
        ReturnCode returnCode = userRelationService.removeFollower(follerId,currentUserId);
        return new WebResult(returnCode);
    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取粉丝列表
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @ApiOperation(value = "/follower/list",notes="获取粉丝列表",httpMethod = "DELETE")
    @PrintUrlAnno
    @DeleteMapping("/follower/list")
    public BaseResult listFollower(){
        Long currentUserId =  UserUtil.getUserId(request);
        List<User> users =  userRelationService.listFollower(currentUserId);

        return new WebResult(RelationReturnCode.REMOVE_FOLLOWER_SUCCESS,users);
    }

    /**
     *功能描述
     * @author lgj
     * @Description  移除关注者
     * @date 6/6/19
     * @param:
     * @return:
     *
     */
    @ApiOperation(value = "/follow",notes="获取关注列表",httpMethod = "DELETE")
    @PrintUrlAnno
    @DeleteMapping("/followee/list")
    public BaseResult listFollowee(){
        Long currentUserId =  UserUtil.getUserId(request);
        List<User> users = userRelationService.listFollowee(currentUserId);

        return new WebResult(RelationReturnCode.REMOVE_FOLLOWER_SUCCESS,users);
    }
}
