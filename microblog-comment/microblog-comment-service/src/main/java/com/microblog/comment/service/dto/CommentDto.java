package com.microblog.comment.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *功能描述 
 * @author lgj
 * @Description   评论的dto，用于返回前端
 * @date 7/18/19
*/
@Data
@Builder
public class CommentDto {

    //评论id
    private long cid;
    //微博id
    private long blogId;
    //回复的评论ID
    private Long replyId;

    private Long replyUserId;
    private String replyUserNickName;

    //上一级的评论ID
    private Long pid;

    //评论用户id
    private long userId;
    //评论用户头像
    private String headerUrl;
    //评论用户昵称
    private String nickName;
    //评论内容
    private String content;
    //评论时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;
    //子评论
    List<CommentDto> child = new ArrayList<>();

}
