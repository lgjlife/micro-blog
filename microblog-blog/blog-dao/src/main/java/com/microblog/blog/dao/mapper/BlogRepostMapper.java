package com.microblog.blog.dao.mapper;

import com.microblog.blog.dao.model.BlogRepost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogRepostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_repost
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long repostId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_repost
     *
     * @mbggenerated
     */
    int insert(BlogRepost record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_repost
     *
     * @mbggenerated
     */
    BlogRepost selectByPrimaryKey(Long repostId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_repost
     *
     * @mbggenerated
     */
    List<BlogRepost> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_repost
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BlogRepost record);


    Long selectCountByBlogId(@Param("blogId") Long blogId);
}