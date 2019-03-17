
/**
* 博客表
*
 */
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`(
   `blog_id` BIGINT   AUTO_INCREMENT COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "用户ID",
   `content`  TEXT  COMMENT "内容",
   `video_url` VARCHAR(100)  default "" COMMENT "视频URL",
   `type` ENUM("PUBLIC","FREINDS","PRIVATE","GROUP")  default "PUBLIC" COMMENT "类型:公开,好友圈,仅自己可见,群可见",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
   `publish_time` DATETIME DEFAULT NULL COMMENT "发布时间",
   `is_original` VARCHAR(5) DEFAULT "true" COMMENT "是否原创",
    PRIMARY KEY (`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客表";

/**
* 博客图片表
 */
DROP TABLE IF EXISTS `blog_img`;
CREATE TABLE `blog_img`(
   `img_id` BIGINT   AUTO_INCREMENT COMMENT "图片ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `img_url` VARCHAR(100)  default "" COMMENT "图片URL",
    PRIMARY KEY (`img_id`),
    index(`blog_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客图片表";

/**
* 博客评论表
 */
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`(
   `comment_id` BIGINT   AUTO_INCREMENT COMMENT "评论ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "评论用户ID",
   `content`  TEXT   COMMENT "内容",
   `publish_time` DATETIME DEFAULT NULL COMMENT "发布时间",
    PRIMARY KEY (`comment_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客评论表";

/**
* 博客收藏表
 */
DROP TABLE IF EXISTS `blog_collect`;
CREATE TABLE `blog_collect`(
   `collect_id` BIGINT   AUTO_INCREMENT COMMENT "转发ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "收藏用户ID",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
    PRIMARY KEY (`collect_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客收藏表";

/**
* 博客转发表
 */
DROP TABLE IF EXISTS `blog_repost`;
CREATE TABLE `blog_repost`(
   `repost_id` BIGINT   AUTO_INCREMENT COMMENT "转发ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "转发用户ID",
   `content`  TEXT   COMMENT "内容",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
    PRIMARY KEY (`repost_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客转发表";

/**
* 博客点赞表
 */
DROP TABLE IF EXISTS `blog_like`;
CREATE TABLE `blog_like`(
   `like_id` BIGINT   AUTO_INCREMENT COMMENT "转发ID",
   `blog_id` BIGINT   COMMENT "博客ID",
   `user_id` BIGINT   COMMENT "点赞用户ID",
   `create_time` DATETIME DEFAULT NULL COMMENT "创建时间",
    PRIMARY KEY (`like_id`),
    index(`blog_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="博客点赞表";



