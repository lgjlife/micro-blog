package com.microblog.blog.service.handler;

import com.microblog.blog.dao.mapper.BlogLikeMapper;
import com.microblog.blog.dao.model.BlogLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *功能描述
 * @author lgj
 * @Description  micro blog  点赞处理
 * @date 5/17/19
*/
@Component
public class BlogLikeHandler {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BlogLikeMapper blogLikeMapper;





    /**
     *功能描述
     * @author lgj
     * @Description  微博点赞处理
     * @date 5/17/19
     * @param:
     * @return:
     *
    */
    public void  blogLike(long userId,long blogId){

        BlogLike blogLike = new BlogLike();

        redisTemplate.opsForHash().put(blogLikeKey(),
                blogLikeHashKey(userId,blogId),
                createBlogLike(userId,blogId));
    }

    private BlogLike createBlogLike(long userId,long blogId){
        BlogLike blogLike = new BlogLike();
        blogLike.setBlogId(blogId);
        blogLike.setUserId(userId);
        blogLike.setCreateTime(new Date());
        return blogLike;

    }

    public void  blogUnLike(long userId,long blogId){

        if(redisTemplate.opsForHash().delete(blogLikeKey(),blogLikeHashKey(userId,blogId)) > 0){
            //删除失败，说明已经写入数据库中
            //写入取消点赞hash
            redisTemplate.opsForHash().put(blogUnLikeKey(),
                    blogLikeHashKey(userId,blogId),userId);
        }



    }

    /**
     *功能描述 
     * @author lgj
     * @Description   点赞信息导入处理
     * @date 5/17/19
     * @param: 
     * @return: 
     *
    */
    public void  blogLikeImportHandler(){

        //插入点赞信息
        Map<String, BlogLike> likeMaps = redisTemplate.opsForHash().entries(blogLikeKey());
        blogLikeMapper.insertMap(likeMaps);

        //移除缓存中的点赞信息
        likeMaps.forEach((k,v)->{
            redisTemplate.opsForHash().delete(blogLikeKey()
                    ,blogLikeHashKey(v.getUserId(),v.getLikeId()));
        });

        //根据删除缓存表删除数据库中的信息

        Map<String, BlogLike> unlikeMaps = redisTemplate.opsForHash().entries(blogUnLikeKey());
        blogLikeMapper.deleteMap(unlikeMaps);

        //删除点赞信息
        likeMaps.forEach((k,v)->{
            redisTemplate.opsForHash().delete(blogUnLikeKey()
                    ,blogLikeHashKey(v.getUserId(),v.getLikeId()));
        });



    }

    /**
     *功能描述
     * @author lgj
     * @Description 评论点赞处理
     * @date 5/17/19
     * @param:
     * @return:
     *
    */
    public void commentLikeHandler(long userId,long commentId){


    }



    /**
     *功能描述
     * @author lgj
     * @Description  评论点赞导入处理
     * @date 5/17/19
     * @param:
     * @return:
     *
    */
    public void commentLikeImportHandler(){

    }


    private String blogLikeKey(){
        return  "blog:like:hash";
    }

    private String blogUnLikeKey(){
        return  "blog:unlike:hash";
    }


    private String blogLikeHashKey(long userId,long blogId){

        return  new StringBuilder().append(blogId).append(":").append(userId).toString();
    }


}
