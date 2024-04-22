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
  CONSTRAINT `t_user_attraction_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_user_attraction_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户景点表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `t_attraction_ticket_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_attraction_ticket_t_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `t_ticket` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点门票表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_attraction_image`
--

DROP TABLE IF EXISTS `t_attraction_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attraction_image` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `attraction_id` bigint NOT NULL COMMENT '景点编号',
  `image_id` bigint NOT NULL COMMENT '图片编号',
  PRIMARY KEY (`id`),
  KEY `t_attraction_image_t_attraction_id_fk` (`attraction_id`),
  KEY `t_attraction_image_t_image_id_fk` (`image_id`),
  CONSTRAINT `t_attraction_image_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_attraction_image_t_image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `t_image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_image`
--

DROP TABLE IF EXISTS `t_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_image` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `image_path` varchar(255) NOT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-22 21:20:18
