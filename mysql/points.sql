/*积分信息表*/
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points`(
   `points_id` BIGINT   AUTO_INCREMENT COMMENT "积分ID",
   `user_id` BIGINT  DEFAULT NULL COMMENT "用户ID",
   `points` BIGINT DEFAULT 0 COMMENT "总积分" ,
    PRIMARY KEY (`points_id`),
    UNIQUE user_id_index (`user_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="积分表";
