package com.cloud.microblog.blog.dao.model;

import java.util.Date;

public class Blog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.blog_id
     *
     * @mbggenerated
     */
    private Long blogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.video_url
     *
     * @mbggenerated
     */
    private String videoUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.publish_time
     *
     * @mbggenerated
     */
    private Date publishTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.is_original
     *
     * @mbggenerated
     */
    private String isOriginal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.blog_id
     *
     * @return the value of blog.blog_id
     *
     * @mbggenerated
     */
    public Long getBlogId() {
        return blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.blog_id
     *
     * @param blogId the value for blog.blog_id
     *
     * @mbggenerated
     */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.user_id
     *
     * @return the value of blog.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.user_id
     *
     * @param userId the value for blog.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.video_url
     *
     * @return the value of blog.video_url
     *
     * @mbggenerated
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.video_url
     *
     * @param videoUrl the value for blog.video_url
     *
     * @mbggenerated
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.type
     *
     * @return the value of blog.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.type
     *
     * @param type the value for blog.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.create_time
     *
     * @return the value of blog.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.create_time
     *
     * @param createTime the value for blog.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.publish_time
     *
     * @return the value of blog.publish_time
     *
     * @mbggenerated
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.publish_time
     *
     * @param publishTime the value for blog.publish_time
     *
     * @mbggenerated
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.is_original
     *
     * @return the value of blog.is_original
     *
     * @mbggenerated
     */
    public String getIsOriginal() {
        return isOriginal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.is_original
     *
     * @param isOriginal the value for blog.is_original
     *
     * @mbggenerated
     */
    public void setIsOriginal(String isOriginal) {
        this.isOriginal = isOriginal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog.content
     *
     * @return the value of blog.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog.content
     *
     * @param content the value for blog.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content;
    }
}