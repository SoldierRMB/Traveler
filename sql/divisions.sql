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
-- Table structure for table `t_areas`
--

DROP TABLE IF EXISTS `t_areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_areas` (
  `code` bigint NOT NULL COMMENT '县级编号',
  `name` varchar(255) DEFAULT NULL COMMENT '县级名称',
  `province_code` bigint DEFAULT NULL COMMENT '省级编号',
  `city_code` bigint DEFAULT NULL COMMENT '地级编号',
  PRIMARY KEY (`code`),
  KEY `t_areas_t_cities_code_fk` (`city_code`),
  KEY `t_areas_t_provinces_code_fk` (`province_code`),
  CONSTRAINT `t_areas_t_cities_code_fk` FOREIGN KEY (`city_code`) REFERENCES `t_cities` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `t_areas_t_provinces_code_fk` FOREIGN KEY (`province_code`) REFERENCES `t_provinces` (`code`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='县级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cities`
--

DROP TABLE IF EXISTS `t_cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_cities` (
  `code` bigint NOT NULL COMMENT '地级编号',
  `name` varchar(255) DEFAULT NULL COMMENT '地级名称',
  `province_code` bigint DEFAULT NULL COMMENT '省级编号',
  PRIMARY KEY (`code`),
  KEY `t_cities_t_provinces_code_fk` (`province_code`),
  CONSTRAINT `t_cities_t_provinces_code_fk` FOREIGN KEY (`province_code`) REFERENCES `t_provinces` (`code`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='地级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_provinces`
--

DROP TABLE IF EXISTS `t_provinces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_provinces` (
  `code` bigint NOT NULL COMMENT '省级编号',
  `name` varchar(255) DEFAULT NULL COMMENT '省级名称',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='省级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_streets`
--

DROP TABLE IF EXISTS `t_streets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_streets` (
  `code` bigint NOT NULL COMMENT '乡级编号',
  `name` varchar(255) DEFAULT NULL COMMENT '乡级名称',
  `province_code` bigint DEFAULT NULL COMMENT '省级编号',
  `city_code` bigint DEFAULT NULL COMMENT '地级编号',
  `area_code` bigint DEFAULT NULL COMMENT '县级编号',
  PRIMARY KEY (`code`),
  KEY `t_streets_t_areas_code_fk` (`area_code`),
  KEY `t_streets_t_cities_code_fk` (`city_code`),
  KEY `t_streets_t_provinces_code_fk` (`province_code`),
  CONSTRAINT `t_streets_t_areas_code_fk` FOREIGN KEY (`area_code`) REFERENCES `t_areas` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `t_streets_t_cities_code_fk` FOREIGN KEY (`city_code`) REFERENCES `t_cities` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `t_streets_t_provinces_code_fk` FOREIGN KEY (`province_code`) REFERENCES `t_provinces` (`code`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='乡级表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-08 17:46:56
