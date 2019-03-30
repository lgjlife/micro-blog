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
  `content` text COMMENT '内容',
  `video_url` varchar(100) DEFAULT '' COMMENT '视频URL',
  `type` enum('PUBLIC','FREINDS','PRIVATE','GROUP') DEFAULT 'PUBLIC' COMMENT '类型:公开,好友圈,仅自己可见,群可见',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_img`
--

LOCK TABLES `blog_img` WRITE;
/*!40000 ALTER TABLE `blog_img` DISABLE KEYS */;
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
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_nums` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'飞扬的天空','77671e8c7374b54bdbfe9b8624aeaf43','b4faae8a1ff14432496e526a3c1d2229',NULL,NULL,NULL,'/img/header/5597-1550725481498.jpg',NULL,'',NULL,'13925716753','2019-02-20 21:56:06',NULL,NULL),(2,NULL,NULL,'3d0c1695ea18510fc6c78b9dda8add64','b9bea755e2629418489a8ab748c6aad5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'13925716752','2019-03-01 01:55:35',NULL,NULL),(3,NULL,NULL,'2247573f3259c6a9587ea44837b136cd','8f51952619b0d97a88f16bca9cd4f16b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'13925716751','2019-03-01 02:19:12',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-30 16:09:41
