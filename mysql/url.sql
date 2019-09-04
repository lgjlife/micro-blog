
DROP TABLE IF EXISTS `url`;
CREATE TABLE `url`(
   `id` BIGINT   AUTO_INCREMENT COMMENT "url id",
   `url` VARCHAR(100)  default "" COMMENT "URL",
    PRIMARY KEY (`id`),
    unique index url(url)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="路径表";

