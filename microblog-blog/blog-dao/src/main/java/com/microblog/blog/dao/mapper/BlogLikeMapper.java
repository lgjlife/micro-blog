package com.microblog.blog.dao.mapper;

import com.microblog.blog.dao.model.BlogLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogLikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_like
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long likeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_like
     *
     * @mbggenerated
     */
    int insert(BlogLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_like
     *
     * @mbggenerated
     */
    BlogLike selectByPrimaryKey(Long likeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_like
     *
     * @mbggenerated
     */
    List<BlogLike> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_like
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BlogLike record);


    Long selectCountByBlogId(@Param("blogId") Long blogId);

    Long insertMap(@Param("likeMaps") Map<String, BlogLike> likeMaps);
    Long deleteMap(@Param("unLikeMaps") Map<String, BlogLike> unLikeMaps);

}