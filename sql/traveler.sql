-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: traveler
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order` (
  `id` bigint unsigned NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `ticket_id` bigint NOT NULL COMMENT '门票编号',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '订单状态 1.待支付 2.已支付 3.已完成 4.已取消',
  `quantity` int NOT NULL COMMENT '门票数量',
  `amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `order_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
  PRIMARY KEY (`id`),
  KEY `t_order_t_ticket_id_fk` (`ticket_id`),
  KEY `t_order_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_order_t_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `t_ticket` (`id`),
  CONSTRAINT `t_order_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `authority` varchar(255) NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_menu_pk` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `comment_type` tinyint NOT NULL COMMENT '评论类型 1.主题评论 2.景点评论',
  `content` text NOT NULL COMMENT '评论内容',
  `from_user_id` bigint NOT NULL COMMENT '评论用户编号',
  `post_id` bigint NOT NULL DEFAULT '0' COMMENT '动态编号 0.非动态评论',
  `viewpoint_id` bigint NOT NULL DEFAULT '0' COMMENT '景点评论 0.非景点评论',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_comment_t_user_id_fk` (`from_user_id`),
  CONSTRAINT `t_comment_t_user_id_fk` FOREIGN KEY (`from_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_attraction_ticket`
--

DROP TABLE IF EXISTS `t_attraction_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attraction_ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '景点门票编号',
  `attraction_id` bigint NOT NULL COMMENT '景点编号',
  `ticket_id` bigint NOT NULL COMMENT '门票编号',
  PRIMARY KEY (`id`),
  KEY `t_attraction_ticket_t_attraction_id_fk` (`attraction_id`),
  KEY `t_attraction_ticket_t_ticket_id_fk` (`ticket_id`),
  CONSTRAINT `t_attraction_ticket_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`),
  CONSTRAINT `t_attraction_ticket_t_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `t_ticket` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点门票表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attraction_ticket`
--

LOCK TABLES `t_attraction_ticket` WRITE;
/*!40000 ALTER TABLE `t_attraction_ticket` DISABLE KEYS */;
INSERT INTO `t_attraction_ticket` VALUES (1,1,1);
INSERT INTO `t_attraction_ticket` VALUES (2,1,2);
INSERT INTO `t_attraction_ticket` VALUES (3,1,3);
INSERT INTO `t_attraction_ticket` VALUES (4,1,4);
INSERT INTO `t_attraction_ticket` VALUES (5,1,5);
INSERT INTO `t_attraction_ticket` VALUES (6,1,6);
INSERT INTO `t_attraction_ticket` VALUES (7,1,7);
INSERT INTO `t_attraction_ticket` VALUES (8,1,8);
INSERT INTO `t_attraction_ticket` VALUES (9,4,9);
INSERT INTO `t_attraction_ticket` VALUES (10,4,10);
INSERT INTO `t_attraction_ticket` VALUES (11,4,11);
INSERT INTO `t_attraction_ticket` VALUES (12,4,12);
INSERT INTO `t_attraction_ticket` VALUES (13,4,13);
INSERT INTO `t_attraction_ticket` VALUES (14,4,14);
/*!40000 ALTER TABLE `t_attraction_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_attraction`
--

DROP TABLE IF EXISTS `t_attraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attraction` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '景点编号',
  `attraction_name` varchar(255) NOT NULL COMMENT '景点名称',
  `description` text NOT NULL COMMENT '景点描述',
  `location` varchar(255) NOT NULL COMMENT '详细地址',
  `score` decimal(3,2) DEFAULT NULL COMMENT '景点评分',
  `province_code` bigint NOT NULL COMMENT '省级编号',
  `city_code` bigint NOT NULL COMMENT '地级编号',
  `area_code` bigint NOT NULL COMMENT '县级编号',
  `street_code` bigint NOT NULL COMMENT '乡级编号',
  `reviewed` tinyint NOT NULL DEFAULT '0' COMMENT '是否审核 0.审核中 1.审核通过 2.审核不通过',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0.未删除 1.已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_attraction_t_areas_code_fk` (`area_code`),
  KEY `t_attraction_t_cities_code_fk` (`city_code`),
  KEY `t_attraction_t_provinces_code_fk` (`province_code`),
  KEY `t_attraction_t_streets_code_fk` (`street_code`),
  CONSTRAINT `t_attraction_t_areas_code_fk` FOREIGN KEY (`area_code`) REFERENCES `t_areas` (`code`),
  CONSTRAINT `t_attraction_t_cities_code_fk` FOREIGN KEY (`city_code`) REFERENCES `t_cities` (`code`),
  CONSTRAINT `t_attraction_t_provinces_code_fk` FOREIGN KEY (`province_code`) REFERENCES `t_provinces` (`code`),
  CONSTRAINT `t_attraction_t_streets_code_fk` FOREIGN KEY (`street_code`) REFERENCES `t_streets` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attraction`
--

LOCK TABLES `t_attraction` WRITE;
/*!40000 ALTER TABLE `t_attraction` DISABLE KEYS */;
INSERT INTO `t_attraction` VALUES (1,'故宫博物院','08:30-16:30开放（15:30停止入园）','北京市东城区景山前街4号',5.00,11,1101,110101,110101002,1,0,'2024-03-15 16:59:30','2024-04-03 14:33:10');
INSERT INTO `t_attraction` VALUES (2,'天坛','06:30-22:00开放（21:00停止入园）','北京市东城区天坛路甲1号',5.00,11,1101,110101,110101016,2,0,'2024-03-17 18:16:32','2024-03-28 20:28:21');
INSERT INTO `t_attraction` VALUES (3,'八达岭长城','07:30-16:00开放','北京市延庆区G6京藏高速58号出口',NULL,11,1101,110119,110119102,1,1,'2024-03-28 14:49:32','2024-03-29 17:50:41');
INSERT INTO `t_attraction` VALUES (4,'沈阳故宫','09:00-16:30开放（15:45停止入园）','沈阳市沈河区沈阳路171号',NULL,21,2101,210103,210103017,0,0,'2024-03-30 22:50:30','2024-04-03 14:52:08');
INSERT INTO `t_attraction` VALUES (5,'张学良旧居','09:00-16:30开放（16:00停止入园）','沈阳市沈河区朝阳街少帅府巷46号',NULL,21,2101,210103,210103017,1,0,'2024-03-30 23:17:23','2024-04-05 13:49:26');
INSERT INTO `t_attraction` VALUES (6,'沈阳方特欢乐世界','10:00-17:00开放','沈阳市沈北新区盛京大街55号',NULL,21,2101,210113,210113004,2,0,'2024-03-30 23:22:16','2024-04-03 14:43:15');
/*!40000 ALTER TABLE `t_attraction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_post`
--

DROP TABLE IF EXISTS `t_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_post` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '动态编号',
  `title` varchar(255) NOT NULL COMMENT '动态标题',
  `content` text NOT NULL COMMENT '动态内容',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_topic_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_post_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post`
--

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_reply`
--

DROP TABLE IF EXISTS `t_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_reply` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '回复编号',
  `comment_id` bigint NOT NULL COMMENT '评论编号',
  `content` text NOT NULL COMMENT '回复内容',
  `from_user_id` bigint NOT NULL COMMENT '回复用户编号',
  `to_user_id` bigint NOT NULL COMMENT '目标用户编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_replay_t_comment_id_fk` (`comment_id`),
  KEY `t_replay_t_user_id_fk` (`from_user_id`),
  KEY `t_replay_t_user_id_fk_2` (`to_user_id`),
  CONSTRAINT `t_replay_t_comment_id_fk` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `t_replay_t_user_id_fk` FOREIGN KEY (`from_user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_replay_t_user_id_fk_2` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_reply`
--

LOCK TABLES `t_reply` WRITE;
/*!40000 ALTER TABLE `t_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_name_zh` varchar(255) NOT NULL COMMENT '中文角色名称',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 0.否 1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'ROLE_ADMIN','系统管理员',0);
INSERT INTO `t_role` VALUES (2,'ROLE_STAFF','景点管理员',0);
INSERT INTO `t_role` VALUES (3,'ROLE_TOURIST','游客用户',0);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `t_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单角色编号',
  `role_id` int NOT NULL COMMENT '角色编号',
  `menu_id` int NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`),
  KEY `t_role_menu_t_menu_id_fk` (`menu_id`),
  KEY `t_role_menu_t_role_id_fk` (`role_id`),
  CONSTRAINT `t_role_menu_t_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `t_role_menu_t_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `t_role_menu` WRITE;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ticket`
--

DROP TABLE IF EXISTS `t_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '门票编号',
  `ticket_name` varchar(255) NOT NULL COMMENT '门票名称',
  `price` decimal(10,2) NOT NULL COMMENT '门票价格',
  `ticket_type` tinyint NOT NULL COMMENT '门票类型 1.全价票 2.优惠票',
  `description` varchar(255) NOT NULL COMMENT '门票描述',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0.未删除 1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='门票表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ticket`
--

LOCK TABLES `t_ticket` WRITE;
/*!40000 ALTER TABLE `t_ticket` DISABLE KEYS */;
INSERT INTO `t_ticket` VALUES (1,'旺季门票',60.00,1,'每年4月1日至10月31日',0);
INSERT INTO `t_ticket` VALUES (2,'旺季门票（老年票）',30.00,2,'60周岁以上（含60周岁）老年人凭有效身份证原件，门票半价优惠',0);
INSERT INTO `t_ticket` VALUES (3,'学生票',20.00,2,'18周岁以上、本科及以下学历（不含成人教育），可购学生票',0);
INSERT INTO `t_ticket` VALUES (4,'淡季门票',40.00,1,'每年11月1日至次年3月31日',0);
INSERT INTO `t_ticket` VALUES (5,'珍宝馆门票',10.00,1,'需单独购买',0);
INSERT INTO `t_ticket` VALUES (6,'钟表馆门票',10.00,1,'需单独购买',0);
INSERT INTO `t_ticket` VALUES (7,'珍宝馆门票（老年票、学生票）',5.00,2,'需单独购买',0);
INSERT INTO `t_ticket` VALUES (8,'钟表馆门票（老年票、学生票）',5.00,2,'需单独购买',0);
INSERT INTO `t_ticket` VALUES (9,'成人票（全价票）',50.00,1,'年龄18周岁（含）~60周岁（不含）',0);
INSERT INTO `t_ticket` VALUES (10,'学生票（半价票）',25.00,2,'全日制本科或本科以下学生凭学生证（不含成人教育、研究生阶段）',0);
INSERT INTO `t_ticket` VALUES (11,'老人票（半价票）',25.00,2,'60岁~69岁',0);
INSERT INTO `t_ticket` VALUES (12,'优待票（沈阳居民专享）',25.00,2,'长期在沈阳居住的游客',0);
INSERT INTO `t_ticket` VALUES (13,'儿童票',0.00,2,'6岁或1.3米以下',0);
INSERT INTO `t_ticket` VALUES (14,'老人票',0.00,2,'70周岁以上',0);
/*!40000 ALTER TABLE `t_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(68) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 0.否 1.是',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_username_uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','admin@soldiersoft.com','超级管理员',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (2,'staff1','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','staff1@soldiersoft.com','景点管理员1',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (3,'staff2','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','staff2@soldiersoft.com','景点管理员2',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (4,'staff3','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','staff3@soldiersoft.com','景点管理员3',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (5,'tourist1','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','tourist1@soldiersoft.com','游客用户1',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (6,'tourist2','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','tourist2@soldiersoft.com','游客用户2',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (7,'tourist3','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','tourist3@soldiersoft.com','游客用户3',0,'2024-03-15 17:44:02','2024-03-15 17:44:02');
INSERT INTO `t_user` VALUES (8,'staff4','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','staff4@soldiersoft.com','景点管理员4',0,'2024-03-30 22:17:32','2024-03-30 22:17:32');
INSERT INTO `t_user` VALUES (9,'staff5','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','staff5@soldiersoft.com','景点管理员5',0,'2024-03-30 22:17:32','2024-03-30 22:17:32');
INSERT INTO `t_user` VALUES (10,'staff6','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','staff6@soldiersoft.com','景点管理员6',0,'2024-03-30 22:17:32','2024-03-30 22:17:32');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_attraction`
--

DROP TABLE IF EXISTS `t_user_attraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_attraction` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户景点编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `attraction_id` bigint NOT NULL COMMENT '景点编号',
  PRIMARY KEY (`id`),
  KEY `t_user_attraction_t_attraction_id_fk` (`attraction_id`),
  KEY `t_user_attraction_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_user_attraction_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`),
  CONSTRAINT `t_user_attraction_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户景点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_attraction`
--

LOCK TABLES `t_user_attraction` WRITE;
/*!40000 ALTER TABLE `t_user_attraction` DISABLE KEYS */;
INSERT INTO `t_user_attraction` VALUES (1,2,1);
INSERT INTO `t_user_attraction` VALUES (2,3,2);
INSERT INTO `t_user_attraction` VALUES (3,4,3);
INSERT INTO `t_user_attraction` VALUES (4,8,4);
INSERT INTO `t_user_attraction` VALUES (5,9,5);
INSERT INTO `t_user_attraction` VALUES (6,10,6);
/*!40000 ALTER TABLE `t_user_attraction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `role_id` int NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `t_user_role_t_role_id_fk` (`role_id`),
  KEY `t_user_role_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_user_role_t_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_user_role_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1,1);
INSERT INTO `t_user_role` VALUES (2,2,2);
INSERT INTO `t_user_role` VALUES (3,3,2);
INSERT INTO `t_user_role` VALUES (4,4,2);
INSERT INTO `t_user_role` VALUES (5,5,3);
INSERT INTO `t_user_role` VALUES (6,6,3);
INSERT INTO `t_user_role` VALUES (7,7,3);
INSERT INTO `t_user_role` VALUES (8,8,2);
INSERT INTO `t_user_role` VALUES (9,9,2);
INSERT INTO `t_user_role` VALUES (10,10,2);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-08 17:02:19
