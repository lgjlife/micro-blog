DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table` (
                              `xid` varchar(128) NOT NULL,
                              `transaction_id` bigint(20) DEFAULT NULL,
                              `status` tinyint(4) NOT NULL,
                              `application_id` varchar(64) DEFAULT NULL,
                              `transaction_service_group` varchar(64) DEFAULT NULL,
                              `transaction_name` varchar(64) DEFAULT NULL,
                              `timeout` int(11) DEFAULT NULL,
                              `begin_time` bigint(20) DEFAULT NULL,
                              `application_data` varchar(2000) DEFAULT NULL,
                              `gmt_create` datetime DEFAULT NULL,
                              `gmt_modified` datetime DEFAULT NULL,
                              PRIMARY KEY (`xid`),
                              KEY `idx_gmt_modified_status` (`gmt_modified`,`status`),
                              KEY `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/**branch_table的表结构**/

DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table` (
                              `branch_id` bigint(20) NOT NULL,
                              `xid` varchar(128) NOT NULL,
                              `transaction_id` bigint(20) DEFAULT NULL,
                              `resource_group_id` varchar(32) DEFAULT NULL,
                              `resource_id` varchar(256) DEFAULT NULL,
                              `lock_key` varchar(128) DEFAULT NULL,
                              `branch_type` varchar(8) DEFAULT NULL,
                              `status` tinyint(4) DEFAULT NULL,
                              `client_id` varchar(64) DEFAULT NULL,
                              `application_data` varchar(2000) DEFAULT NULL,
                              `gmt_create` datetime DEFAULT NULL,
                              `gmt_modified` datetime DEFAULT NULL,
                              PRIMARY KEY (`branch_id`),
                              KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/**lock_table的表结构**/

DROP TABLE IF EXISTS `lock_table`;
create table `lock_table` (
                            `row_key` varchar(128) not null,
                            `xid` varchar(96),
                            `transaction_id` long ,
                            `branch_id` long,
                            `resource_id` varchar(256) ,
                            `table_name` varchar(32) ,
                            `pk` varchar(32) ,
                            `gmt_create` datetime ,
                            `gmt_modified` datetime,
                            primary key(`row_key`)
);

/*业务表**/

/*购物车表**/
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间戳',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '删除标志 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;
-- 初始化数据
INSERT INTO `cart` VALUES (1, 1, 1, 1590114829756, 1590114829756, 0);
INSERT INTO `goods` VALUES (1, '键盘', 100, 100.00, 1590132270000, 1590377130, 0);

INSERT INTO `cart` VALUES (2, 1, 1, 1590114829756, 1590114829756, 0);

/*商品表*/
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `stock_num` bigint(20) NULL DEFAULT NULL COMMENT '商品库存数量',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间戳',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标志 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;
-- 初始化数据
INSERT INTO `goods` VALUES (1, '键盘', 100, 100.00, 1590132270000, 1590377130, 0);


/*账户表*/
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间戳',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标志 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包表' ROW_FORMAT = Dynamic;
-- 初始化数据
INSERT INTO `wallet` VALUES (1, 1, 500.00, 1590132270000, 1590377130, 0);

/*订单表*/
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

