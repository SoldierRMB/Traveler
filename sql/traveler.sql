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
-- Table structure for table `t_rating`
--

DROP TABLE IF EXISTS `t_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_rating` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评分编号',
                            `rating` enum('1','2','3','4','5') NOT NULL COMMENT '评分',
                            `content` varchar(255) NOT NULL COMMENT '评价内容',
                            `user_id` bigint NOT NULL COMMENT '用户编号',
                            `order_id` bigint unsigned NOT NULL COMMENT '订单编号',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            KEY `t_rating_t_order_id_fk` (`order_id`),
                            KEY `t_rating_t_user_id_fk` (`user_id`),
                            CONSTRAINT `t_rating_t_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`) ON DELETE CASCADE,
                            CONSTRAINT `t_rating_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评分表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_rating`
--

LOCK TABLES `t_rating` WRITE;
/*!40000 ALTER TABLE `t_rating` DISABLE KEYS */;
INSERT INTO `t_rating` VALUES (1,'5','故宫太棒了',5,1779445283005394945,'2024-05-04 15:11:12','2024-05-04 15:11:12');
/*!40000 ALTER TABLE `t_rating` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='门票表';
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
INSERT INTO `t_ticket` VALUES (9,'联票',34.00,1,'18周岁（含）-59周岁游客携本人身份证，含门票及景点（祈年殿、回音壁、圜丘）',0);
INSERT INTO `t_ticket` VALUES (10,'优惠联票',17.00,2,'18周岁以上全日制本科及以下学历学生携身份证和学生证，含门票及景点（祈年殿、回音壁、圜丘）',0);
INSERT INTO `t_ticket` VALUES (11,'门票',15.00,1,'18周岁（含）-59周岁游客携本人身份证，不含景点',0);
INSERT INTO `t_ticket` VALUES (12,'优惠门票',7.50,2,'18周岁以上全日制本科及以下学历学生携身份证和学生证，不含景点',0);
INSERT INTO `t_ticket` VALUES (13,'景点票',20.00,1,'18周岁（含）-59周岁游客携本人身份证18周岁（含）-59周岁游客携本人身份证，不含门票仅限景点（祈年殿、回音壁、圜丘）',0);
INSERT INTO `t_ticket` VALUES (14,'优惠景点票',10.00,2,'18周岁以上全日制本科及以下学历学生携身份证和学生证，不含门票仅限景点（祈年殿、回音壁、圜丘）',0);
INSERT INTO `t_ticket` VALUES (15,'政策性免费票',0.00,2,'为方便游客，政策性免费游客凭身份证等有效证件验证入园，无需购票',0);
INSERT INTO `t_ticket` VALUES (16,'八达岭长城成人票',40.00,1,'禁止携带宠物入园',0);
INSERT INTO `t_ticket` VALUES (17,'八达岭长城学生票',20.00,2,'禁止携带宠物入园，18周岁（含）-25周岁（含）全日制大学本科及以下学历（不含成教等）凭有效证件',0);
INSERT INTO `t_ticket` VALUES (18,'60周岁（含）以上老人票',0.00,2,'禁止携带宠物入园，60周岁（含）以上老人，凭有效证件',0);
INSERT INTO `t_ticket` VALUES (19,'未成年人免费票',0.00,2,'禁止携带宠物入园，未满18周岁未成年人，凭有效证件',0);
INSERT INTO `t_ticket` VALUES (20,'成人票（全价票）',50.00,1,'年龄18周岁（含）~60周岁（不含）',0);
INSERT INTO `t_ticket` VALUES (21,'学生票（半价票）',25.00,2,'全日制本科或本科以下学生凭学生证（不含成人教育、研究生阶段）',0);
INSERT INTO `t_ticket` VALUES (22,'老人票（半价票）',25.00,2,'60岁~69岁',0);
INSERT INTO `t_ticket` VALUES (23,'优待票（沈阳居民专享）',25.00,2,'长期在沈阳居住的游客',0);
INSERT INTO `t_ticket` VALUES (24,'儿童票',0.00,2,'6岁或1.3米以下',0);
INSERT INTO `t_ticket` VALUES (25,'老人票',0.00,2,'70周岁以上',0);
INSERT INTO `t_ticket` VALUES (26,'成人票',48.00,1,'适用条件：年龄18周岁（含）~60周岁（不含）（按生日计算年龄，以出行日期为准）；补充说明：家长携带未成年人（18周岁（不含）以下）子女参观的游客，家长正常购票，其携带的未成年子女可享受免费',0);
INSERT INTO `t_ticket` VALUES (27,'学生票',24.00,2,'适用条件：年龄60周岁（含）~69周岁（含）（按生日计算年龄，以出行日期为准）',0);
INSERT INTO `t_ticket` VALUES (28,'老人票',24.00,2,'适用条件：全日制大中小学生（不含成人教育、研究生）凭学生证；如与家长一起出行的未成年人（18周岁（不含）以下），家长正常购票，其携带的未成年子女可享受免费；1.3米（含）以上未成年人独自参观的，需要购买学生票',0);
INSERT INTO `t_ticket` VALUES (29,'成人票',280.00,1,'适用条件：年龄65周岁（不含）以下（按生日计算年龄，以出行日期为准）；身高140cm（不含）以上；需同时满足以上条件',0);
INSERT INTO `t_ticket` VALUES (30,'儿童票（110cm（含）~140cm（含））',180.00,2,'适用条件：身高110cm（含）~140cm（含）',0);
INSERT INTO `t_ticket` VALUES (31,'学生票（大学生票）',230.00,2,'适用条件：年龄18周岁（含）~24周岁（含）（按生日计算年龄，以出行日期为准）；补充说明：适用于全日制在校大学生人群（含本科、专科）凭购票时所登记的身份证件原件至乐园主入口验证入园，并携带学生证件前往乐园以备查验，如超过年龄，凭有效证件可以到窗口购买',0);
INSERT INTO `t_ticket` VALUES (32,'老人票',180.00,2,'适用条件：年龄65周岁（含）以上（按生日计算年龄，以出行日期为准）',0);
INSERT INTO `t_ticket` VALUES (33,'儿童票（110cm（不含）以下）',0.00,2,'适用条件：身高110cm（不含）以下',0);
/*!40000 ALTER TABLE `t_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_attraction_image`
--

DROP TABLE IF EXISTS `t_attraction_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attraction_image` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '景点图片编号',
                                      `attraction_id` bigint NOT NULL COMMENT '景点编号',
                                      `image_id` bigint NOT NULL COMMENT '图片编号',
                                      PRIMARY KEY (`id`),
                                      KEY `t_attraction_image_t_attraction_id_fk` (`attraction_id`),
                                      KEY `t_attraction_image_t_image_id_fk` (`image_id`),
                                      CONSTRAINT `t_attraction_image_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`) ON DELETE CASCADE,
                                      CONSTRAINT `t_attraction_image_t_image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `t_image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attraction_image`
--

LOCK TABLES `t_attraction_image` WRITE;
/*!40000 ALTER TABLE `t_attraction_image` DISABLE KEYS */;
INSERT INTO `t_attraction_image` VALUES (1,1,1);
INSERT INTO `t_attraction_image` VALUES (2,2,2);
INSERT INTO `t_attraction_image` VALUES (3,3,3);
INSERT INTO `t_attraction_image` VALUES (4,4,4);
INSERT INTO `t_attraction_image` VALUES (5,5,5);
INSERT INTO `t_attraction_image` VALUES (6,6,6);
/*!40000 ALTER TABLE `t_attraction_image` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
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
INSERT INTO `t_user` VALUES (11,'tourist4','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','tourist4@soldiersoft.com','游客用户4',0,'2024-05-06 00:33:16','2024-05-06 00:33:16');
INSERT INTO `t_user` VALUES (12,'tourist5','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','tourist5@soldiersoft.com','游客用户5',0,'2024-05-06 00:33:16','2024-05-06 00:33:16');
INSERT INTO `t_user` VALUES (13,'tourist6','{bcrypt}$2a$10$/2SbcbzpsRERu7eTiB34be91gOyk479hCIsoKqIUOUqTJmiT5NXOO','tourist6@soldiersoft.com','游客用户6',0,'2024-05-06 00:33:16','2024-05-06 00:33:16');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
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
                          CONSTRAINT `t_post_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post`
--

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;
INSERT INTO `t_post` VALUES (1,'游览故宫体验','在故宫的游览中，仿佛穿越了时光的长河。沿着红墙黄瓦，步入千年历史的宫殿之中，古老的建筑展示着华夏文明的灿烂辉煌。每一处雕梁画栋都诉说着历史的沧桑，每一件珍宝都闪耀着皇室的辉煌。游人穿梭于廊檐下，感受着古老文明的魅力，仿佛与历史亲密接触。',5,'2024-05-01 11:51:02','2024-05-04 13:36:47');
INSERT INTO `t_post` VALUES (2,'游览天坛心得','天坛，古老而庄严，是北京的标志性建筑之一。我踏入其内，仿佛穿越时光，置身于历史的怀抱中。\n首先映入眼帘的是祈年殿，巍峨庄严，屹立于青砖红墙之间，展现着古代建筑的恢弘气势。踏上祈年殿的台阶，心中不禁生起一股肃然之感。\n接着，我来到圜丘，圜丘是天地交融之地，是古代皇帝祭天祈谷的场所。在这里，我静静地凝望着那座圆形的祭坛，感受着历史的沉淀和庄严的氛围。\n最后，我来到了祈年门前，俯瞰整个天坛公园，远处的琉璃瓦闪烁着阳光，近处的参拜者络绎不绝，一幅和谐美丽的画面尽收眼底。\n天坛，不仅仅是一处旅游景点，更是中华民族的文化符号和精神家园。在这里，我领略到了中华文化的博大精深，也感受到了历史的庄严和厚重。这次的游览，让我对中国传统文化有了更深的理解和认识。',6,'2024-05-05 23:39:25','2024-05-06 00:06:51');
INSERT INTO `t_post` VALUES (3,'沈阳美食推荐','沈阳是中国东北地区的重要城市，也是美食之都之一，这里有丰富多样的美食选择，尤其以东北特色菜肴为主。以下是一些沈阳的美食推荐：\n沈阳炸酱面：炸酱面是沈阳的招牌美食之一，以面条搭配浓郁的酱料、黄瓜丝和豆腐等配料而闻名。口感丰富，味道地道，是沈阳人最爱的美食之一。\n东北水饺：东北水饺以其馅料丰富、皮薄馅大而著称。在沈阳，你可以品尝到各种口味的水饺，如酸菜肉馅、韭菜鸡蛋馅等，每一口都是满满的幸福感。\n老边饺子：老边饺子是沈阳的传统小吃，以其独特的馅料和制作工艺而备受赞誉。这里的饺子馅料新鲜，口感鲜美，绝对值得一试。\n东北烤肉：沈阳有许多地道的烤肉店，供应各种肉类和蔬菜，配以独特的调料和烤制技巧，非常美味。尤其是在冬季，和朋友们一起享受烤肉是一种常见的休闲方式。\n锅包肉：锅包肉是一道典型的东北菜，是由薄片的猪肉裹上面粉炸制而成，再淋上特制的酱汁，外脆内嫩，香甜可口。\n东北冷面：夏天来沈阳，别忘了尝尝当地的冷面。清凉爽口的冷面配以各种蔬菜和调料，是夏日消暑的最佳选择。\n锅包皮：锅包皮是沈阳的传统小吃之一，类似于北京的炸酱面，但是味道和做法略有不同。锅包皮的面饼薄而脆，配以酱料和蔬菜，味道独特。\n东北菜：除了以上几种特色小吃外，沈阳还有许多地道的东北菜可供选择，如东北炖菜、炖排骨、红烧肉等，都是值得一试的美食。\n以上推荐的美食只是沈阳丰富美食中的一部分，希望你有机会来沈阳品尝这些地道美味！',11,'2024-05-06 00:35:19','2024-05-06 00:35:19');
/*!40000 ALTER TABLE `t_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_comment` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论编号',
                             `content` text NOT NULL COMMENT '评论内容',
                             `user_id` bigint NOT NULL COMMENT '评论用户编号',
                             `post_id` bigint NOT NULL COMMENT '动态编号 0.非动态评论',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `t_comment_t_user_id_fk` (`user_id`),
                             KEY `t_comment_t_post_id_fk` (`post_id`),
                             CONSTRAINT `t_comment_t_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `t_comment_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (1,'故宫真的是世界奇迹！',11,5,'2024-05-02 13:57:38','2024-05-02 13:57:38');
INSERT INTO `t_comment` VALUES (2,'天坛环境真的很不错！',12,5,'2024-05-04 16:54:46','2024-05-04 16:54:46');
INSERT INTO `t_comment` VALUES (3,'沈阳美食真的很不错！',13,5,'2024-05-04 20:50:08','2024-05-04 20:50:08');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

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
                           `status` tinyint NOT NULL DEFAULT '1' COMMENT '订单状态 1.待支付 2.已支付 3.已完成 4.已评价 5.已取消 6.已删除',
                           `quantity` int NOT NULL COMMENT '门票数量',
                           `amount` decimal(10,2) NOT NULL COMMENT '订单金额',
                           `order_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
                           PRIMARY KEY (`id`),
                           KEY `t_order_t_ticket_id_fk` (`ticket_id`),
                           KEY `t_order_t_user_id_fk` (`user_id`),
                           CONSTRAINT `t_order_t_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `t_ticket` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `t_order_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (1779445283005394945,5,1,6,1,60.00,'2024-04-14 17:43:10');
INSERT INTO `t_order` VALUES (1779531104270041090,5,3,3,1,20.00,'2024-04-14 23:24:12');
INSERT INTO `t_order` VALUES (1780071406505734145,6,9,2,1,50.00,'2024-04-16 11:11:10');
INSERT INTO `t_order` VALUES (1780116994844733442,7,4,3,1,40.00,'2024-04-16 14:12:19');
INSERT INTO `t_order` VALUES (1784221563757494273,5,1,3,1,60.00,'2024-04-27 22:02:24');
INSERT INTO `t_order` VALUES (1784221593650298882,5,1,2,1,60.00,'2024-04-27 22:02:31');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_attraction_announcement`
--

DROP TABLE IF EXISTS `t_attraction_announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attraction_announcement` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '景点公告编号',
                                             `title` varchar(255) NOT NULL COMMENT '景点公告标题',
                                             `content` varchar(255) NOT NULL COMMENT '景点公告内容',
                                             `attraction_id` bigint NOT NULL COMMENT '景点编号',
                                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                             PRIMARY KEY (`id`),
                                             KEY `t_attraction_announcement_t_attraction_id_fk` (`attraction_id`),
                                             CONSTRAINT `t_attraction_announcement_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attraction_announcement`
--

LOCK TABLES `t_attraction_announcement` WRITE;
/*!40000 ALTER TABLE `t_attraction_announcement` DISABLE KEYS */;
INSERT INTO `t_attraction_announcement` VALUES (1,'预订须知','故宫每周一闭馆，其他时间正常开放。',1,'2024-05-05 21:16:40','2024-05-05 21:16:40');
INSERT INTO `t_attraction_announcement` VALUES (2,'预订须知','天坛每周一闭馆，其他时间正常开放。',2,'2024-05-05 21:16:40','2024-05-05 21:16:40');
/*!40000 ALTER TABLE `t_attraction_announcement` ENABLE KEYS */;
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
-- Table structure for table `t_image`
--

DROP TABLE IF EXISTS `t_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_image` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片编号',
                           `image_path` varchar(255) NOT NULL COMMENT '图片路径',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_image`
--

LOCK TABLES `t_image` WRITE;
/*!40000 ALTER TABLE `t_image` DISABLE KEYS */;
INSERT INTO `t_image` VALUES (1,'D:\\home\\traveler\\image\\98d647bb-ae6d-45fa-9428-eb2d28a41478.jpg');
INSERT INTO `t_image` VALUES (2,'D:\\home\\traveler\\image\\68dd8fdb-3a8e-411b-8da6-72b83d86251a.jpg');
INSERT INTO `t_image` VALUES (3,'D:\\home\\traveler\\image\\c5c543ca-840c-438f-81bb-42db9ea35b2b.jpg');
INSERT INTO `t_image` VALUES (4,'D:\\home\\traveler\\image\\7b399e69-5c58-458a-824f-fdd0364e9695.jpg');
INSERT INTO `t_image` VALUES (5,'D:\\home\\traveler\\image\\01701c40-10a9-4c36-b4b9-be677ddc97a1.jpg');
INSERT INTO `t_image` VALUES (6,'D:\\home\\traveler\\image\\3545b42d-f213-4926-98b5-77336539e333.jpg');
/*!40000 ALTER TABLE `t_image` ENABLE KEYS */;
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
                                       CONSTRAINT `t_attraction_ticket_t_attraction_id_fk` FOREIGN KEY (`attraction_id`) REFERENCES `t_attraction` (`id`) ON DELETE CASCADE,
                                       CONSTRAINT `t_attraction_ticket_t_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `t_ticket` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点门票表';
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
INSERT INTO `t_attraction_ticket` VALUES (9,2,9);
INSERT INTO `t_attraction_ticket` VALUES (10,2,10);
INSERT INTO `t_attraction_ticket` VALUES (11,2,11);
INSERT INTO `t_attraction_ticket` VALUES (12,2,12);
INSERT INTO `t_attraction_ticket` VALUES (13,2,13);
INSERT INTO `t_attraction_ticket` VALUES (14,2,14);
INSERT INTO `t_attraction_ticket` VALUES (15,2,15);
INSERT INTO `t_attraction_ticket` VALUES (16,3,16);
INSERT INTO `t_attraction_ticket` VALUES (17,3,17);
INSERT INTO `t_attraction_ticket` VALUES (18,3,18);
INSERT INTO `t_attraction_ticket` VALUES (19,3,19);
INSERT INTO `t_attraction_ticket` VALUES (20,4,20);
INSERT INTO `t_attraction_ticket` VALUES (21,4,21);
INSERT INTO `t_attraction_ticket` VALUES (22,4,22);
INSERT INTO `t_attraction_ticket` VALUES (23,4,23);
INSERT INTO `t_attraction_ticket` VALUES (24,4,24);
INSERT INTO `t_attraction_ticket` VALUES (25,4,25);
INSERT INTO `t_attraction_ticket` VALUES (26,5,26);
INSERT INTO `t_attraction_ticket` VALUES (27,5,27);
INSERT INTO `t_attraction_ticket` VALUES (28,5,28);
INSERT INTO `t_attraction_ticket` VALUES (29,6,29);
INSERT INTO `t_attraction_ticket` VALUES (30,6,30);
INSERT INTO `t_attraction_ticket` VALUES (31,6,31);
INSERT INTO `t_attraction_ticket` VALUES (32,6,32);
INSERT INTO `t_attraction_ticket` VALUES (33,6,33);
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
                                `rating` decimal(3,2) DEFAULT NULL COMMENT '景点评分',
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
INSERT INTO `t_attraction` VALUES (1,'故宫博物院','08:30-16:30开放（15:30停止入园）','北京市东城区景山前街4号',5.00,11,1101,110101,110101002,1,0,'2024-03-15 16:59:30','2024-05-04 15:11:12');
INSERT INTO `t_attraction` VALUES (2,'天坛','06:30-22:00开放（21:00停止入园）','北京市东城区天坛路甲1号',NULL,11,1101,110101,110101016,1,0,'2024-03-17 18:16:32','2024-05-05 22:05:02');
INSERT INTO `t_attraction` VALUES (3,'八达岭长城','07:30-16:00开放','北京市延庆区G6京藏高速58号出口',NULL,11,1101,110119,110119102,1,1,'2024-03-28 14:49:32','2024-03-29 17:50:41');
INSERT INTO `t_attraction` VALUES (4,'沈阳故宫','09:00-16:30开放（15:45停止入园）','沈阳市沈河区沈阳路171号',NULL,21,2101,210103,210103017,1,0,'2024-03-30 22:50:30','2024-05-05 22:05:02');
INSERT INTO `t_attraction` VALUES (5,'张学良旧居','09:00-16:30开放（16:00停止入园）','沈阳市沈河区朝阳街少帅府巷46号',NULL,21,2101,210103,210103017,1,0,'2024-03-30 23:17:23','2024-04-05 13:49:26');
INSERT INTO `t_attraction` VALUES (6,'沈阳方特欢乐世界','10:00-17:00开放','沈阳市沈北新区盛京大街55号',NULL,21,2101,210113,210113004,1,0,'2024-03-30 23:22:16','2024-05-05 22:05:02');
/*!40000 ALTER TABLE `t_attraction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_announcement`
--

DROP TABLE IF EXISTS `t_announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_announcement` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告编号',
                                  `title` varchar(255) NOT NULL COMMENT '公告标题',
                                  `content` varchar(255) NOT NULL COMMENT '公告内容',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_announcement`
--

LOCK TABLES `t_announcement` WRITE;
/*!40000 ALTER TABLE `t_announcement` DISABLE KEYS */;
INSERT INTO `t_announcement` VALUES (1,'【行者】旅游在线预订平台','您可以浏览旅游动态哦~','2024-05-05 21:14:08','2024-05-05 21:14:08');
INSERT INTO `t_announcement` VALUES (2,'【行者】旅游在线预订平台','您可以预订需要的景点门票哦~','2024-05-05 21:14:09','2024-05-05 21:14:09');
/*!40000 ALTER TABLE `t_announcement` ENABLE KEYS */;
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
                           CONSTRAINT `t_replay_t_comment_id_fk` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `t_replay_t_user_id_fk` FOREIGN KEY (`from_user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `t_replay_t_user_id_fk_2` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
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
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色编号',
                               `user_id` bigint NOT NULL COMMENT '用户编号',
                               `role_id` int NOT NULL COMMENT '角色编号',
                               PRIMARY KEY (`id`),
                               KEY `t_user_role_t_role_id_fk` (`role_id`),
                               KEY `t_user_role_t_user_id_fk` (`user_id`),
                               CONSTRAINT `t_user_role_t_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
                               CONSTRAINT `t_user_role_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';
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
INSERT INTO `t_user_role` VALUES (11,11,3);
INSERT INTO `t_user_role` VALUES (12,12,3);
INSERT INTO `t_user_role` VALUES (13,13,3);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
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
                                     CONSTRAINT `t_user_attraction_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
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

-- Dump completed on 2024-05-06  0:36:30
