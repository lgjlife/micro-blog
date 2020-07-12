
DROP TABLE IF EXISTS `manager_user`;
CREATE TABLE `manager_user`(
   `id` BIGINT   AUTO_INCREMENT COMMENT "管理员ID",
   `username`  VARCHAR(30) DEFAULT NULL COMMENT "姓名",
   `password` VARCHAR(50) NOT NULL COMMENT "登录密码",
   `salt` VARCHAR(50) DEFAULT NULL COMMENT "登录密码盐",
   `phone` VARCHAR(20)  DEFAULT NULL  COMMENT "电话号码",
   `email` VARCHAR(30) COMMENT "电子邮箱",
   `email_active` TINYINT DEFAULT NULL COMMENT "邮箱是否激活",
   `header_url` VARCHAR(100) DEFAULT NULL COMMENT "头像URL",
   `create_time` TIMESTAMP DEFAULT  CURRENT_TIMESTAMP COMMENT "注册时间",
   `login_time` DATETIME DEFAULT NULL COMMENT "登录时间",
   `last_login_time` DATETIME DEFAULT NULL COMMENT "最后登录时间",
   `gender`  VARCHAR(10) DEFAULT NULL COMMENT "性别",
   `age`  TINYINT UNSIGNED DEFAULT NULL  COMMENT "年龄",


   /*0:未注册，1：已注册，2：登录状态，3：离线状态，4,禁止登录状态，5：注销状态）*/
   `status` TINYINT DEFAULT NULL COMMENT "状态",
    PRIMARY KEY (`id`),
    INDEX username_index(username)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="管理员用户表";

insert into `manager_user` (id,username,password,salt) values(1,"李白","2a4f8daffbebcd38794ed54a7d95a2b9","d4gfr45xc8r");