package com.cloud.microblog.blog.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BlogInfoDto {

    private Long  blogId;
    private String headerUrl;
    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date publishTime;
    private String content;
    private String[] blogImg;
    private int collectNum;
    private int repostNum;
    private int commentNum;
    private int likeNum;

    public  static  BlogInfoDto build(){
        return  new BlogInfoDto();
    }

    public BlogInfoDto blogId(Long blogId){

        this.nickName  = nickName;
        return  this;
    }

    public BlogInfoDto headerUrl(String headerUrl){

        this.headerUrl  = headerUrl;
        return  this;
    }

    public BlogInfoDto nickName(String nickName){

        this.nickName  = nickName;
        return  this;
    }

    public BlogInfoDto publishTime(Date publishTime){

        this.publishTime  = publishTime;
        return  this;
    }

    public BlogInfoDto content(String content){

        this.content  = content;
        return  this;
    }

    public BlogInfoDto blogImg(String[] blogImg){

        this.blogImg  = blogImg;
        return  this;
    }

    public BlogInfoDto collectNum(int collectNum){

        this.collectNum  = collectNum;
        return  this;
    }

    public BlogInfoDto commentNum(int commentNum){

        this.commentNum  = commentNum;
        return  this;
    }

    public BlogInfoDto likeNum(int likeNum){

        this.likeNum  = likeNum;
        return  this;
    }
    public BlogInfoDto repostNum(int repostNum){

        this.repostNum  = repostNum;
        return  this;
    }

}
