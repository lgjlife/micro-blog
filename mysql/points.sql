/*积分信息表*/
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points`(
   `points_id` BIGINT   AUTO_INCREMENT COMMENT "积分ID",
   `user_id` BIGINT  DEFAULT NULL COMMENT "用户ID",
   `points` BIGINT DEFAULT 0 COMMENT "总积分" ,
    PRIMARY KEY (`points_id`),
    UNIQUE user_id_index (`user_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="积分表";

DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign`(
   `id` BIGINT   AUTO_INCREMENT COMMENT "ID",
   `user_id` BIGINT  DEFAULT NULL COMMENT "用户ID",
   `sign_history` VARCHAR(46) DEFAULT NULL COMMENT "签到历史",
   `sign_count` INT DEFAULT 0 COMMENT "连续签到次数" ,
   `year` INT DEFAULT NULL COMMENT "年份" ,
   `last_sign_time` TIMESTAMP DEFAULT  CURRENT_TIMESTAMP COMMENT "最后签到时间",
    PRIMARY KEY (`id`),
    UNIQUE user_id_index (`user_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="签到表";