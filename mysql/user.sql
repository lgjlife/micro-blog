/*用户信息表*/
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
   `user_id` BIGINT   AUTO_INCREMENT COMMENT "用户ID",
   `user_number` BIGINT  DEFAULT NULL COMMENT "用户编号",
   `nick_name`  VARCHAR(30) DEFAULT NULL COMMENT "昵称" ,
   `login_password` VARCHAR(50) NOT NULL COMMENT "登录密码",
   `salt` VARCHAR(50) DEFAULT NULL COMMENT "登录密码盐",
   `actual_name` VARCHAR(100) DEFAULT NULL COMMENT "真实姓名",
   `gender`  VARCHAR(10) DEFAULT NULL COMMENT "性别",
   `age`  TINYINT UNSIGNED DEFAULT NULL  COMMENT "年龄",
   `header_url` VARCHAR(50) DEFAULT NULL COMMENT "头像URL",
   /*0:未注册，1：已注册，2：登录状态，3：离线状态，4,禁止登录状态，5：注销状态）*/
   `status` TINYINT DEFAULT NULL COMMENT "状态",
   `email` VARCHAR(30) COMMENT "电子邮箱",
   `email_active` TINYINT DEFAULT NULL COMMENT "邮箱是否激活",
   `phone_num` VARCHAR(20)  DEFAULT NULL  COMMENT "电话号码",
   `register_time` DATETIME DEFAULT NULL COMMENT "注册时间",
   `last_login_time` DATETIME DEFAULT NULL COMMENT "最后登录时间",
   `login_nums`  INT DEFAULT NULL COMMENT "登录次数",
    PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="用户表";

/*用户粉丝表
* 一对多，一个用户有多个粉丝
*/
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans`(
   `fans_id` BIGINT   AUTO_INCREMENT COMMENT "粉丝ID",
   `user_id` BIGINT   COMMENT "用户ID",
    PRIMARY KEY (`fans_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="粉丝表";


/*用户关注表
* 一对多，一个用户关注多个微博
*/

DROP TABLE IF EXISTS `concerns`;
CREATE TABLE `concerns`(
   `concerns_id` BIGINT   AUTO_INCREMENT COMMENT "关注ID",
   `user_id` BIGINT   COMMENT "用户ID",
    PRIMARY KEY (`concerns_id`),
    index(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="用户关注表";

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
   `img_id` TINYINT   AUTO_INCREMENT COMMENT "图片ID",
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



