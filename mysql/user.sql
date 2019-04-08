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
   `register_time` TIMESTAMP DEFAULT  CURRENT_TIMESTAMP COMMENT "注册时间",
   `last_login_time` DATETIME DEFAULT NULL COMMENT "最后登录时间",
   `update_time` TIMESTAMP DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "注册时间",
   `login_nums`  INT DEFAULT NULL COMMENT "登录次数",
    PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="用户表";

INSERT INTO `user` VALUES (1,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常阿瑟斯的',1,'13925716751','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);
INSERT INTO `user` VALUES (2,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常三个地方',1,'13925716752','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);
INSERT INTO `user` VALUES (3,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常速度更算法',1,'13925716753','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);
INSERT INTO `user` VALUES (4,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常的活动',1,'13925716754','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);
INSERT INTO `user` VALUES (5,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常算法大给',1,'13925716755','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);
INSERT INTO `user` VALUES (6,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常分公司的',1,'13925716756','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);

UPDATE user  SET  nick_name="new name" where user_id = 1;

DROP TABLE IF EXISTS `user_delete`;
CREATE TABLE `user_delete`(
   `id` BIGINT   AUTO_INCREMENT COMMENT "删除用户ID",
   `user_id` BIGINT  DEFAULT NULL COMMENT "用户ID",
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="用户删除表";


/*用户记录删除时将删除的用户ID存放到删除表，搜索引擎会将搜索引擎中的数据同步删除*/
DROP TRIGGER IF EXISTS `user_update_trigger`;
CREATE TRIGGER user_update_trigger AFTER DELETE
ON user  FOR EACH ROW
BEGIN
INSERT  INTO user_delete (user_id) values(OLD.user_id);
END;

INSERT INTO `user` VALUES (22,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',
'','',1,'/img/header/5597-1550725481498.jpg',1,'非常分公司的',1,'13925716753','2019-02-20 21:56:06','2019-02-20 21:56:06','2019-02-20 21:56:06',1);

DELETE FROM user where user_id=22;


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
