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
INSERT INTO `t_attraction` VALUES (1,'故宫博物院','08:30-16:30开放（15:30停止入园）','北京市东城区景山前街4号',5.00,11,1101,110101,110101002,1,1,'2024-03-15 16:59:30','2024-04-01 00:31:13');
INSERT INTO `t_attraction` VALUES (2,'天坛','06:30-22:00开放（21:00停止入园）','北京市东城区天坛路甲1号',5.00,11,1101,110101,110101016,1,0,'2024-03-17 18:16:32','2024-03-28 20:28:21');
INSERT INTO `t_attraction` VALUES (3,'八达岭长城','07:30-16:00开放','北京市延庆区G6京藏高速58号出口',NULL,11,1101,110119,110119102,1,1,'2024-03-28 14:49:32','2024-03-29 17:50:41');
INSERT INTO `t_attraction` VALUES (4,'沈阳故宫','09:00-16:30开放（15:45停止入园）','沈阳市沈河区沈阳路171号',NULL,21,2101,210103,210103017,0,0,'2024-03-30 22:50:30','2024-03-30 22:50:30');
INSERT INTO `t_attraction` VALUES (5,'张学良旧居','09:00-16:30开放（16:00停止入园）','沈阳市沈河区朝阳街少帅府巷46号',NULL,21,2101,210103,210103017,0,0,'2024-03-30 23:17:23','2024-03-30 23:17:23');
INSERT INTO `t_attraction` VALUES (6,'沈阳方特欢乐世界','10:00-17:00开放','沈阳市沈北新区盛京大街55号',NULL,21,2101,210113,210113004,0,0,'2024-03-30 23:22:16','2024-03-30 23:22:16');
/*!40000 ALTER TABLE `t_attraction` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-01 14:50:32
