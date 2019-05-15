-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: microblog
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `blog_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `video_url` varchar(100) DEFAULT '' COMMENT '视频URL',
  `type` enum('PUBLIC','FREINDS','PRIVATE','GROUP') DEFAULT 'PUBLIC' COMMENT '类型:公开,好友圈,仅自己可见,群可见',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `is_original` varchar(5) DEFAULT 'true' COMMENT '是否原创',
  PRIMARY KEY (`blog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_`
--

DROP TABLE IF EXISTS `blog_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_` (
  `like_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '转发ID',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '点赞用户ID',
  PRIMARY KEY (`like_id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_`
--

LOCK TABLES `blog_` WRITE;
/*!40000 ALTER TABLE `blog_` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_collect`
--

DROP TABLE IF EXISTS `blog_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_collect` (
  `collect_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '转发ID',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '收藏用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`collect_id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_collect`
--

LOCK TABLES `blog_collect` WRITE;
/*!40000 ALTER TABLE `blog_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_comment`
--

DROP TABLE IF EXISTS `blog_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '评论用户ID',
  `content` text COMMENT '内容',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`comment_id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_comment`
--

LOCK TABLES `blog_comment` WRITE;
/*!40000 ALTER TABLE `blog_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_img`
--

DROP TABLE IF EXISTS `blog_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_img` (
  `img_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博客ID',
  `img_url` varchar(100) DEFAULT '' COMMENT '图片URL',
  PRIMARY KEY (`img_id`),
  KEY `blog_id` (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='博客图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_img`
--

LOCK TABLES `blog_img` WRITE;
/*!40000 ALTER TABLE `blog_img` DISABLE KEYS */;
INSERT INTO `blog_img` VALUES (1,1,'/img/blog/3/8943-1554781102945.jpg'),(2,1,'/img/blog/3/7332-1554781406453.jpg'),(3,1,'/img/blog/3/8090-1554781538549.jpg'),(4,1,'/img/blog/3/7360-1554782486320.jpg'),(5,15,'/img/blog/3/3441-1554782751918.jpg'),(6,16,'/img/blog/3/4547-1554783043411.jpg'),(7,16,'/img/blog/3/4059-1554783043411.jpg'),(8,16,'/img/blog/3/7062-1554783043411.jpg'),(9,16,'/img/blog/3/289-1554783043412.jpg'),(10,16,'/img/blog/3/9138-1554783043412.jpg'),(11,16,'/img/blog/3/7667-1554783043412.jpg'),(12,16,'/img/blog/3/8367-1554783043412.jpg'),(13,16,'/img/blog/3/5086-1554783043412.jpg'),(14,17,'/img/blog/3/5114-1554789818283.jpg'),(15,18,'/img/blog/3/5408-1554790020198.png'),(16,19,'/img/blog/3/8208-1554790690030.png'),(17,20,'/img/blog/3/6950-1554790751325.png'),(18,21,'/img/blog/3/5441-1554790756642.png');
/*!40000 ALTER TABLE `blog_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_like`
--

DROP TABLE IF EXISTS `blog_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_like` (
  `like_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '转发ID',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '点赞用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`like_id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_like`
--

LOCK TABLES `blog_like` WRITE;
/*!40000 ALTER TABLE `blog_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_repost`
--

DROP TABLE IF EXISTS `blog_repost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_repost` (
  `repost_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '转发ID',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '转发用户ID',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`repost_id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客转发表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_repost`
--

LOCK TABLES `blog_repost` WRITE;
/*!40000 ALTER TABLE `blog_repost` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_repost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concerns`
--

DROP TABLE IF EXISTS `concerns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concerns` (
  `concerns_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关注ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`concerns_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concerns`
--

LOCK TABLES `concerns` WRITE;
/*!40000 ALTER TABLE `concerns` DISABLE KEYS */;
/*!40000 ALTER TABLE `concerns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity_demo`
--

DROP TABLE IF EXISTS `entity_demo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_demo` (
  `demo_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `content` text COMMENT '内容',
  `video_url` varchar(100) DEFAULT '' COMMENT '视频URL',
  `type` enum('PUBLIC','FREINDS','PRIVATE','GROUP') DEFAULT 'PUBLIC' COMMENT '类型:公开,好友圈,仅自己可见,群可见',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `is_original` varchar(5) DEFAULT 'true' COMMENT '是否原创',
  PRIMARY KEY (`demo_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='博客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity_demo`
--

LOCK TABLES `entity_demo` WRITE;
/*!40000 ALTER TABLE `entity_demo` DISABLE KEYS */;
INSERT INTO `entity_demo` VALUES (1,100,'由腾讯推出的QQ浏览器微信版（微信浏览器）进行登录时保留了网页版微信通过二维码登录的方式，但是微信界面将不再占用单独的浏览器标签页，而是变成左侧的边栏。这样的方便用户浏览网页的同时，使用微信。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(2,1002,'微信网页版虽然已经在内测中，只有使用同样在内测中的微信iPhone4.2版扫\n描官方页面上的二维码，才能登录微信网页版，这种登录方式据传会是登录微信网页版的方式，那就是说，每一次都要使用手机扫描一下网页上的二维码，才能登录微信网页版。手机微信客户端变成了微信网页版的登录钥匙','/vide0/1','PUBLIC','2019-03-02 01:55:35','2019-03-03 01:55:35','TRUE'),(3,1003,'首先打开微信网页版，微信网页版的地址，打开页面，就能看见页面正中央一个巨大的二维码。','/vide0/1','PUBLIC','2019-03-04 01:55:35','2019-03-01 01:55:35','TRUE'),(4,1004,'然后扫描微信二维码：以安卓手机为例，在微信页面的“微信”两个字的右边有一个像一支笔四颗星的按钮，点一下这个按钮，就有个扫描二维码的选项，用手机对准屏幕进行扫描，（也可以存下2维码图片以备随时扫描）就可以了。这时，会在微信页面上出现网页版的对话框，发送和收取信息，跟手机都是同步的，很好用的。然后用浏览器生成桌面图标。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(5,1005,'首先下载“网页微信客户端”，大家可以直接到微信官方网站下载。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(6,1006,'打开“网页微信客户端”，会出现跟我们登陆微信页面版一样的二维码登陆界面。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(7,1007,'当我们用打开手机微信，点击“发现→扫一扫”功能，扫描电脑上的“微信客户端”二维码，然后点击确认登陆即可。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(8,1008,'微信主界面以侧边栏形式展示聊天联系人，与网页左右并存','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(9,1009,'当和某个联系人聊天时，聊天面板会并列在联系人面板的右边，浮在网页之上。点击网页区域，聊天面板会自动收起。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(10,1010,'如果电脑屏幕较宽，建议点击右上角的图钉按钮，固定住聊天面板。就可以方便的分屏操作，左屏聊微信，右屏看网页啦。','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE'),(11,1011,'点击联系人面板右上角的新建聊天按钮，就可以选择与单个人聊天，或多个人群聊','/vide0/1','PUBLIC','2019-03-01 01:55:35','2019-03-01 01:55:35','TRUE');
/*!40000 ALTER TABLE `entity_demo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fans`
--

DROP TABLE IF EXISTS `fans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fans` (
  `fans_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '粉丝ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`fans_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='粉丝表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fans`
--

LOCK TABLES `fans` WRITE;
/*!40000 ALTER TABLE `fans` DISABLE KEYS */;
/*!40000 ALTER TABLE `fans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `mail` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `address` varchar(30) DEFAULT NULL COMMENT '昵称',
  `eaddress` varchar(80) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,532701,'广东省深圳市宝安区','guangdong shenzhen baoan'),(2,532702,'广东省深圳市宝安区','guangdong shenzhen baoan'),(3,532703,'广东省深圳市龙岗区','guangdong longgang baoan'),(4,532704,'广东省深圳市龙岗区','guangdong longgang baoan'),(5,532705,'广东省深圳市福田区','guangdong futian baoan'),(6,532706,'广东省深圳市福田区','guangdong futian baoan');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `points`
--

DROP TABLE IF EXISTS `points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `points` (
  `points_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '积分ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `points` bigint(20) DEFAULT '0' COMMENT '总积分',
  PRIMARY KEY (`points_id`),
  UNIQUE KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points`
--

LOCK TABLES `points` WRITE;
/*!40000 ALTER TABLE `points` DISABLE KEYS */;
/*!40000 ALTER TABLE `points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_number` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `login_password` varchar(50) NOT NULL COMMENT '登录密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '登录密码盐',
  `actual_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `header_url` varchar(50) DEFAULT NULL COMMENT '头像URL',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `email_active` tinyint(4) DEFAULT NULL COMMENT '邮箱是否激活',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_nums` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229','','',1,'/img/header/5597-1550725481498.jpg',1,'非常阿瑟斯的',1,'13925716751','2019-02-20 13:56:06','2019-02-20 21:56:06','2019-02-20 13:56:06',1),(2,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229','','',1,'/img/header/5597-1550725481498.jpg',1,'非常三个地方',1,'13925716752','2019-02-20 13:56:06','2019-02-20 21:56:06','2019-02-20 13:56:06',1),(3,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229','','',1,'/img/header/5597-1550725481498.jpg',1,'非常速度更算法',1,'13925716753','2019-02-20 13:56:06','2019-02-20 21:56:06','2019-02-20 13:56:06',1),(4,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229','','',1,'/img/header/5597-1550725481498.jpg',1,'非常的活动',1,'13925716754','2019-02-20 13:56:06','2019-02-20 21:56:06','2019-02-20 13:56:06',1),(5,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229','','',1,'/img/header/5597-1550725481498.jpg',1,'非常算法大给',1,'13925716755','2019-02-20 13:56:06','2019-02-20 21:56:06','2019-02-20 13:56:06',1),(6,1,'中国速读法反攻倒算','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229','','',1,'/img/header/5597-1550725481498.jpg',1,'非常分公司的',1,'13925716756','2019-02-20 13:56:06','2019-02-20 21:56:06','2019-02-20 13:56:06',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_delete`
--

DROP TABLE IF EXISTS `user_delete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_delete` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '删除用户ID',
  `u_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户删除表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_delete`
--

LOCK TABLES `user_delete` WRITE;
/*!40000 ALTER TABLE `user_delete` DISABLE KEYS */;
INSERT INTO `user_delete` VALUES (1,20),(2,21),(3,22);
/*!40000 ALTER TABLE `user_delete` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-15 11:53:02
