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
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(68) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 0.否 1.是',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_username_uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
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
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';
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
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_viewpoint`
--

DROP TABLE IF EXISTS `t_user_viewpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_viewpoint` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户景点编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `viewpoint_id` bigint NOT NULL COMMENT '景点编号',
  PRIMARY KEY (`id`),
  KEY `t_user_viewpoint_t_user_id_fk` (`user_id`),
  KEY `t_user_viewpoint_t_viewpoint_id_fk` (`viewpoint_id`),
  CONSTRAINT `t_user_viewpoint_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_user_viewpoint_t_viewpoint_id_fk` FOREIGN KEY (`viewpoint_id`) REFERENCES `t_viewpoint` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户景点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_viewpoint`
--

LOCK TABLES `t_user_viewpoint` WRITE;
/*!40000 ALTER TABLE `t_user_viewpoint` DISABLE KEYS */;
INSERT INTO `t_user_viewpoint` VALUES (1,2,1);
INSERT INTO `t_user_viewpoint` VALUES (2,2,2);
/*!40000 ALTER TABLE `t_user_viewpoint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_viewpoint`
--

DROP TABLE IF EXISTS `t_viewpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_viewpoint` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '景点编号',
  `viewpoint_name` varchar(255) NOT NULL COMMENT '景点名称',
  `description` text NOT NULL COMMENT '景点描述',
  `location` varchar(255) NOT NULL COMMENT '详细地址',
  `score` double DEFAULT NULL COMMENT '景点评分',
  `province_code` bigint NOT NULL COMMENT '省级编号',
  `city_code` bigint NOT NULL COMMENT '地级编号',
  `area_code` bigint NOT NULL COMMENT '县级编号',
  `street_code` bigint NOT NULL COMMENT '乡级编号',
  `reviewed` tinyint NOT NULL DEFAULT '0' COMMENT '是否审核 0.审核中 1.审核通过 2.审核不通过',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0.未删除 1.已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_viewpoint_t_areas_code_fk` (`area_code`),
  KEY `t_viewpoint_t_cities_code_fk` (`city_code`),
  KEY `t_viewpoint_t_provinces_code_fk` (`province_code`),
  KEY `t_viewpoint_t_streets_code_fk` (`street_code`),
  CONSTRAINT `t_viewpoint_t_areas_code_fk` FOREIGN KEY (`area_code`) REFERENCES `t_areas` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `t_viewpoint_t_cities_code_fk` FOREIGN KEY (`city_code`) REFERENCES `t_cities` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `t_viewpoint_t_provinces_code_fk` FOREIGN KEY (`province_code`) REFERENCES `t_provinces` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `t_viewpoint_t_streets_code_fk` FOREIGN KEY (`street_code`) REFERENCES `t_streets` (`code`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_viewpoint`
--

LOCK TABLES `t_viewpoint` WRITE;
/*!40000 ALTER TABLE `t_viewpoint` DISABLE KEYS */;
INSERT INTO `t_viewpoint` VALUES (1,'故宫博物院','08:30-16:30开放（15:30停止入园）','北京市东城区景山前街4号',NULL,11,1101,110101,110101002,1,0,'2024-03-15 16:59:30','2024-03-17 17:41:39');
INSERT INTO `t_viewpoint` VALUES (2,'天坛','06:30-22:00开放（21:00停止入园）','北京市东城区天坛路甲1号',NULL,11,1101,110101,110101016,1,0,'2024-03-17 18:16:32','2024-03-17 18:20:31');
/*!40000 ALTER TABLE `t_viewpoint` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-20 13:02:34
