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


CREATE TABLE `tb_cart`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间戳',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '删除标志 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;
-- 初始化数据
INSERT INTO `tb_cart` VALUES (1, 1, 1, 1590114829756, 1590114829756, 0);

CREATE TABLE `tb_goods`  (
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
INSERT INTO `tb_goods` VALUES (1, '键盘', 100, 100.00, 1590132270000, 1590377130, 0);

CREATE TABLE `tb_wallet`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间戳',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标志 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包表' ROW_FORMAT = Dynamic;
-- 初始化数据
INSERT INTO `tb_wallet` VALUES (1, 1, 500.00, 1590132270000, 1590377130, 0);

CREATE TABLE `tb_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除 0：未删除；1：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

