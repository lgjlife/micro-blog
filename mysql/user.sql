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
