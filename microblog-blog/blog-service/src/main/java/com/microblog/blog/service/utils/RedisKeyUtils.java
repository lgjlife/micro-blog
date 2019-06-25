package com.microblog.blog.service.utils;

public class RedisKeyUtils {

    public static String getBlogLikeCount(long blogId,long date){

        return new StringBuilder("blog:").append("like:").append("count:")
                .append(blogId).append(":").append(date).toString();
    }

    public static String getBlogListCount(long blogId,long date){

        return new StringBuilder("blog:").append("like:").append("list:")
                .append(blogId).append(":").append(date).toString();
    }


}
