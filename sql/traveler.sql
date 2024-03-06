-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: traveler
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @old_character_set_client = @@character_set_client */;
/*!40101 SET @old_character_set_results = @@character_set_results */;
/*!40101 SET @old_collation_connection = @@collation_connection */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @old_time_zone = @@time_zone */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @old_unique_checks = @@unique_checks, UNIQUE_CHECKS = 0 */;
/*!40014 SET @old_foreign_key_checks = @@foreign_key_checks, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @old_sql_mode = @@sql_mode, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @old_sql_notes = @@sql_notes, SQL_NOTES = 0 */;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `T_COMMENT`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_COMMENT`
(
    `ID`           bigint   NOT NULL AUTO_INCREMENT COMMENT '评论编号',
    `COMMENT_TYPE` tinyint  NOT NULL COMMENT '评论类型 1.主题评论 2.景点评论',
    `CONTENT`      text     NOT NULL COMMENT '评论内容',
    `FROM_USER_ID` bigint   NOT NULL COMMENT '评论用户编号',
    `TOPIC_ID`     bigint   NOT NULL DEFAULT '0' COMMENT '主题编号 0.非主题评论',
    `VIEWPOINT_ID` bigint   NOT NULL DEFAULT '0' COMMENT '景点评论 0.非景点评论',
    `CREATE_TIME`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    KEY `T_COMMENT_T_USER_ID_FK` (`FROM_USER_ID`),
    CONSTRAINT `T_COMMENT_T_USER_ID_FK` FOREIGN KEY (`FROM_USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `T_COMMENT` WRITE;
/*!40000 ALTER TABLE `T_COMMENT`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `T_COMMENT`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `T_MENU`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_MENU`
(
    `ID`        int         NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
    `AUTHORITY` varchar(50) NOT NULL COMMENT '权限',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `T_MENU_PK` (`AUTHORITY`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `T_MENU` WRITE;
/*!40000 ALTER TABLE `T_MENU`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `T_MENU`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_reply`
--

DROP TABLE IF EXISTS `T_REPLY`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_REPLY`
(
    `ID`           bigint   NOT NULL AUTO_INCREMENT COMMENT '回复编号',
    `COMMENT_ID`   bigint   NOT NULL COMMENT '评论编号',
    `CONTENT`      text     NOT NULL COMMENT '回复内容',
    `FROM_USER_ID` bigint   NOT NULL COMMENT '回复用户编号',
    `TO_USER_ID`   bigint   NOT NULL COMMENT '目标用户编号',
    `CREATE_TIME`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    KEY `T_REPLAY_T_COMMENT_ID_FK` (`COMMENT_ID`),
    KEY `T_REPLAY_T_USER_ID_FK` (`FROM_USER_ID`),
    KEY `T_REPLAY_T_USER_ID_FK_2` (`TO_USER_ID`),
    CONSTRAINT `T_REPLAY_T_COMMENT_ID_FK` FOREIGN KEY (`COMMENT_ID`) REFERENCES `T_COMMENT` (`ID`),
    CONSTRAINT `T_REPLAY_T_USER_ID_FK` FOREIGN KEY (`FROM_USER_ID`) REFERENCES `T_USER` (`ID`),
    CONSTRAINT `T_REPLAY_T_USER_ID_FK_2` FOREIGN KEY (`TO_USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_reply`
--

LOCK TABLES `T_REPLY` WRITE;
/*!40000 ALTER TABLE `T_REPLY`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `T_REPLY`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `T_ROLE`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_ROLE`
(
    `ID`           int         NOT NULL AUTO_INCREMENT COMMENT '角色编号',
    `ROLE_NAME`    varchar(20) NOT NULL COMMENT '角色名称',
    `ROLE_NAME_ZH` varchar(20) NOT NULL COMMENT '中文角色名称',
    `IS_DISABLE`   tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否禁用 0.否 1.是',
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `T_ROLE` WRITE;
/*!40000 ALTER TABLE `T_ROLE`
    DISABLE KEYS */;
INSERT INTO `T_ROLE`
VALUES (1, 'ROLE_ADMIN', '系统管理员', 0),
       (2, 'ROLE_STAFF', '景点管理员', 0),
       (3, 'ROLE_TOURIST', '游客用户', 0);
/*!40000 ALTER TABLE `T_ROLE`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `T_ROLE_MENU`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_ROLE_MENU`
(
    `ID`      int NOT NULL AUTO_INCREMENT COMMENT '菜单角色编号',
    `ROLE_ID` int NOT NULL COMMENT '角色编号',
    `MENU_ID` int NOT NULL COMMENT '菜单编号',
    PRIMARY KEY (`ID`),
    KEY `T_ROLE_MENU_T_MENU_ID_FK` (`MENU_ID`),
    KEY `T_ROLE_MENU_T_ROLE_ID_FK` (`ROLE_ID`),
    CONSTRAINT `T_ROLE_MENU_T_MENU_ID_FK` FOREIGN KEY (`MENU_ID`) REFERENCES `T_MENU` (`ID`),
    CONSTRAINT `T_ROLE_MENU_T_ROLE_ID_FK` FOREIGN KEY (`ROLE_ID`) REFERENCES `T_ROLE` (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `T_ROLE_MENU` WRITE;
/*!40000 ALTER TABLE `T_ROLE_MENU`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `T_ROLE_MENU`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_topic`
--

DROP TABLE IF EXISTS `T_TOPIC`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_TOPIC`
(
    `ID`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主题编号',
    `TITLE`       varchar(50) NOT NULL COMMENT '主题标题',
    `CONTENT`     text        NOT NULL COMMENT '主题内容',
    `USER_ID`     bigint      NOT NULL COMMENT '用户编号',
    `CREATE_TIME` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    KEY `T_TOPIC_T_USER_ID_FK` (`USER_ID`),
    CONSTRAINT `T_TOPIC_T_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='主题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic`
--

LOCK TABLES `T_TOPIC` WRITE;
/*!40000 ALTER TABLE `T_TOPIC`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `T_TOPIC`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `T_USER`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_USER`
(
    `ID`          bigint      NOT NULL AUTO_INCREMENT COMMENT '用户编号',
    `USERNAME`    varchar(20) NOT NULL COMMENT '用户名',
    `PASSWORD`    varchar(68) NOT NULL COMMENT '密码',
    `EMAIL`       varchar(50)          DEFAULT NULL COMMENT '邮箱',
    `NICKNAME`    varchar(20)          DEFAULT NULL COMMENT '昵称',
    `IS_DISABLE`  tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否禁用 0.否 1.是',
    `CREATE_TIME` datetime    NOT NULL DEFAULT (NOW()) COMMENT '创建时间',
    `UPDATE_TIME` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `T_USERNAME_UK` (`USERNAME`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `T_USER` WRITE;
/*!40000 ALTER TABLE `T_USER`
    DISABLE KEYS */;
INSERT INTO `T_USER`
VALUES (1, 'admin', '{bcrypt}$2a$10$1eUoZ3MjvQD6d8S5m8YV8etFut3LPA9ShsTLbEQGP2bUMdTL0bzFy', 'admin@soldiersoft.com',
        '超级管理员', 0, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
       (2, 'staff', '{bcrypt}$2a$10$1eUoZ3MjvQD6d8S5m8YV8etFut3LPA9ShsTLbEQGP2bUMdTL0bzFy', 'staff@soldiersoft.com',
        '景点管理员', 0, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
       (3, 'tourist', '{bcrypt}$2a$10$1eUoZ3MjvQD6d8S5m8YV8etFut3LPA9ShsTLbEQGP2bUMdTL0bzFy', 'tourist@soldiersoft.com',
        '游客用户', 0, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
       (4, 'staff1', '{bcrypt}$2a$10$ZZu5SN2JL15Mz8MAaJNb4OnvbzUwTOc0BQ8d7WRaesQNJnrXHXCf2', 'staff1@soldiersoft.com',
        NULL, 0, '2024-03-06 15:58:22', '2024-03-06 17:29:29'),
       (5, 'tourist1', '{bcrypt}$2a$10$HEiXnuDXtPYIPfV2a4P3B.rj1Gg/nkWxXZom2IwYiSAKCV9aQL506',
        'tourist1@soldiersoft.com', NULL, 0, '2024-03-06 17:28:31', '2024-03-06 17:29:29');
/*!40000 ALTER TABLE `T_USER`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `T_USER_ROLE`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_USER_ROLE`
(
    `ID`      int    NOT NULL AUTO_INCREMENT COMMENT '用户角色编号',
    `USER_ID` bigint NOT NULL COMMENT '用户编号',
    `ROLE_ID` int    NOT NULL COMMENT '角色编号',
    PRIMARY KEY (`ID`),
    KEY `T_USER_ROLE_T_ROLE_ID_FK` (`ROLE_ID`),
    KEY `T_USER_ROLE_T_USER_ID_FK` (`USER_ID`),
    CONSTRAINT `T_USER_ROLE_T_ROLE_ID_FK` FOREIGN KEY (`ROLE_ID`) REFERENCES `T_ROLE` (`ID`),
    CONSTRAINT `T_USER_ROLE_T_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `T_USER_ROLE` WRITE;
/*!40000 ALTER TABLE `T_USER_ROLE`
    DISABLE KEYS */;
INSERT INTO `T_USER_ROLE`
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 2),
       (5, 5, 3);
/*!40000 ALTER TABLE `T_USER_ROLE`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_viewpoint`
--

DROP TABLE IF EXISTS `T_VIEWPOINT`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_VIEWPOINT`
(
    `ID`             bigint NOT NULL AUTO_INCREMENT COMMENT '景点编号',
    `VIEWPOINT_NAME` varchar(40) DEFAULT NULL COMMENT '景点名称',
    `DESCRIPTION`    text COMMENT '景点描述',
    `SCORE`          double NOT NULL COMMENT '景点评分',
    `PROVINCE_CODE`  bigint NOT NULL COMMENT '省级编号',
    `CITY_CODE`      bigint NOT NULL COMMENT '地级编号',
    `AREA_CODE`      bigint NOT NULL COMMENT '县级编号',
    `STREET_CODE`    bigint NOT NULL COMMENT '乡级编号',
    PRIMARY KEY (`ID`),
    KEY `T_VIEWPOINT_T_AREAS_CODE_FK` (`AREA_CODE`),
    KEY `T_VIEWPOINT_T_CITIES_CODE_FK` (`CITY_CODE`),
    KEY `T_VIEWPOINT_T_PROVINCES_CODE_FK` (`PROVINCE_CODE`),
    KEY `T_VIEWPOINT_T_STREETS_CODE_FK` (`STREET_CODE`),
    CONSTRAINT `T_VIEWPOINT_T_AREAS_CODE_FK` FOREIGN KEY (`AREA_CODE`) REFERENCES `T_AREAS` (`CODE`) ON UPDATE CASCADE,
    CONSTRAINT `T_VIEWPOINT_T_CITIES_CODE_FK` FOREIGN KEY (`CITY_CODE`) REFERENCES `T_CITIES` (`CODE`) ON UPDATE CASCADE,
    CONSTRAINT `T_VIEWPOINT_T_PROVINCES_CODE_FK` FOREIGN KEY (`PROVINCE_CODE`) REFERENCES `T_PROVINCES` (`CODE`) ON UPDATE CASCADE,
    CONSTRAINT `T_VIEWPOINT_T_STREETS_CODE_FK` FOREIGN KEY (`STREET_CODE`) REFERENCES `T_STREETS` (`CODE`) ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='景点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_viewpoint`
--

LOCK TABLES `T_VIEWPOINT` WRITE;
/*!40000 ALTER TABLE `T_VIEWPOINT`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `T_VIEWPOINT`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @old_time_zone */;

/*!40101 SET SQL_MODE = @old_sql_mode */;
/*!40014 SET FOREIGN_KEY_CHECKS = @old_foreign_key_checks */;
/*!40014 SET UNIQUE_CHECKS = @old_unique_checks */;
/*!40101 SET CHARACTER_SET_CLIENT = @old_character_set_client */;
/*!40101 SET CHARACTER_SET_RESULTS = @old_character_set_results */;
/*!40101 SET COLLATION_CONNECTION = @old_collation_connection */;
/*!40111 SET SQL_NOTES = @old_sql_notes */;

-- Dump completed on 2024-03-06 17:32:19
