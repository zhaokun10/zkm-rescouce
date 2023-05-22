-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: zkm
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `add_friend_notification`
--

DROP TABLE IF EXISTS `add_friend_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_friend_notification` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '好友添加通知id',
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL,
  `is_agree` int DEFAULT NULL,
  `last_update_time` timestamp NULL DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='添加好友通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_friend_notification`
--

LOCK TABLES `add_friend_notification` WRITE;
/*!40000 ALTER TABLE `add_friend_notification` DISABLE KEYS */;
INSERT INTO `add_friend_notification` VALUES (1,3,1,1,NULL,NULL),(2,7,3,1,NULL,NULL),(5,7,6,1,NULL,NULL),(16,28,1,1,NULL,NULL);
/*!40000 ALTER TABLE `add_friend_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_record`
--

DROP TABLE IF EXISTS `chat_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '聊天记录id',
  `user_id` int DEFAULT NULL,
  `friend_id` int DEFAULT NULL COMMENT '好友id',
  `group_id` int DEFAULT NULL COMMENT '属于那个群组',
  `chat_record_type_id` int DEFAULT NULL,
  `readed` int DEFAULT '0' COMMENT '是否已读',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '内容',
  `send_user` int DEFAULT NULL,
  `send_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `chat_record_type_id` (`chat_record_type_id`),
  CONSTRAINT `chat_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_zkm` (`id`),
  CONSTRAINT `chat_record_ibfk_2` FOREIGN KEY (`chat_record_type_id`) REFERENCES `chat_record_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_record`
--

LOCK TABLES `chat_record` WRITE;
/*!40000 ALTER TABLE `chat_record` DISABLE KEYS */;
INSERT INTO `chat_record` VALUES (1,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(2,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(3,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(4,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(5,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(6,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(7,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(8,1,2,NULL,1,1,'1',1,'2023-03-03 14:45:36'),(28,2,1,NULL,1,1,'sadasdasdas',2,'2023-03-03 14:45:36'),(29,2,1,NULL,1,1,'asdasdasd',2,'2023-03-03 14:45:41'),(30,2,1,NULL,1,1,'asdasd',2,'2023-03-03 14:49:22'),(31,2,1,NULL,1,1,'sdasda',2,'2023-03-03 14:49:39'),(32,1,2,NULL,1,1,'ssssss',1,'2023-03-06 10:45:10'),(33,1,2,NULL,1,1,'sssss',1,'2023-03-06 10:45:31'),(34,1,2,NULL,1,1,'aaa',1,'2023-03-06 10:46:47'),(35,1,2,NULL,1,1,'2222',1,'2023-03-06 10:49:44'),(36,1,2,NULL,1,1,'222',1,'2023-03-06 10:49:51'),(37,1,2,NULL,1,1,'222',1,'2023-03-06 10:50:21'),(38,1,2,NULL,1,1,'ssss',1,'2023-03-06 10:50:48'),(45,28,1,NULL,1,1,'111',28,'2023-03-08 08:31:01'),(46,1,28,NULL,1,1,'222',1,'2023-03-08 08:31:11');
/*!40000 ALTER TABLE `chat_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_record_type`
--

DROP TABLE IF EXISTS `chat_record_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_record_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '聊天记录类型id',
  `type` int DEFAULT NULL COMMENT '1: 文本 2:视频 3:文件 4:语音',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天记录类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_record_type`
--

LOCK TABLES `chat_record_type` WRITE;
/*!40000 ALTER TABLE `chat_record_type` DISABLE KEYS */;
INSERT INTO `chat_record_type` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `chat_record_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firend_group`
--

DROP TABLE IF EXISTS `firend_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firend_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '好友分组id',
  `user_id` int DEFAULT NULL,
  `friend_group_name` varchar(16) NOT NULL COMMENT '分组名字',
  `friend_group_type` int NOT NULL COMMENT '分组类型',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `firend_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_zkm` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友分组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firend_group`
--

LOCK TABLES `firend_group` WRITE;
/*!40000 ALTER TABLE `firend_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `firend_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '好友列表id',
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL COMMENT '好友id',
  `friend_group_id` int DEFAULT NULL COMMENT '分组id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_zkm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,1,2,NULL),(7,2,1,NULL),(8,3,1,NULL),(9,1,3,NULL),(10,7,3,NULL),(11,3,7,NULL),(12,7,6,NULL),(13,6,7,NULL),(24,28,1,NULL),(25,1,28,NULL);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '群组id',
  `user_id` int DEFAULT NULL,
  `group_name` varchar(16) NOT NULL COMMENT '群组名字',
  `avatar` varchar(255) DEFAULT NULL COMMENT '群组头像',
  `group_group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_group_id` (`group_group_id`),
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_zkm` (`id`),
  CONSTRAINT `group_ibfk_2` FOREIGN KEY (`group_group_id`) REFERENCES `group_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群聊表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_group`
--

DROP TABLE IF EXISTS `group_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '群组分组id',
  `user_id` int DEFAULT NULL,
  `group_group_name` varchar(16) NOT NULL COMMENT '分组名字',
  `group_group_type` int NOT NULL COMMENT '分组类型',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `group_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_zkm` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群聊分组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_group`
--

LOCK TABLES `group_group` WRITE;
/*!40000 ALTER TABLE `group_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_member` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '群组成员数量id',
  `member_id` int DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group_member_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `user_zkm` (`id`),
  CONSTRAINT `group_member_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群聊成员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readed`
--

DROP TABLE IF EXISTS `readed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readed` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `friend_id` int DEFAULT NULL,
  `last_readed_time` timestamp NULL DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='已读表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readed`
--

LOCK TABLES `readed` WRITE;
/*!40000 ALTER TABLE `readed` DISABLE KEYS */;
INSERT INTO `readed` VALUES (1,1,2,'2023-04-25 03:19:44',0),(2,1,2,'2023-04-25 03:19:47',1),(3,2,1,'2023-03-06 10:50:36',0),(4,2,1,'2023-03-06 10:50:57',1),(7,3,1,'2023-03-04 03:54:30',0),(8,3,1,'2023-03-04 03:54:31',1),(9,1,3,'2023-04-25 03:17:06',0),(10,1,3,'2023-04-25 03:17:08',1),(11,1,20,'2023-03-07 11:25:05',0),(12,1,20,'2023-03-07 11:25:53',1),(17,26,1,'2023-03-08 08:19:11',0),(18,1,26,'2023-03-08 08:21:10',0),(19,1,26,'2023-03-08 08:25:01',1),(20,26,1,'2023-03-08 08:20:00',1),(21,27,1,'2023-03-08 08:26:08',0),(22,1,27,'2023-03-08 08:25:38',0),(23,1,27,'2023-03-08 08:26:01',1),(24,27,1,'2023-03-08 08:26:08',1),(25,28,1,'2023-03-08 08:31:32',0),(26,1,28,'2023-04-25 03:19:48',0),(27,28,1,'2023-03-08 08:31:33',1),(28,1,28,'2023-04-25 03:29:01',1);
/*!40000 ALTER TABLE `readed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_zkm`
--

DROP TABLE IF EXISTS `user_zkm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_zkm` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `online` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_zkm`
--

LOCK TABLES `user_zkm` WRITE;
/*!40000 ALTER TABLE `user_zkm` DISABLE KEYS */;
INSERT INTO `user_zkm` VALUES (1,'zk','$2a$10$7/4DRUyulIXvd1JXjXqIV.nkqoGcraBAFixy82GhOn4hnaV93MaLG','zkm','https://cdn.quasar.dev/img/avatar3.jpg','281937@qq.com',1),(2,'tom','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','Tom','https://cdn.quasar.dev/img/avatar5.jpg','cc',1),(3,'mike','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','Mike','https://cdn.quasar.dev/img/avatar5.jpg','cc',1),(4,'willson','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','willson','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(5,'susan','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','susan','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(6,'jimi','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','jimi','https://cdn.quasar.dev/img/avatar5.jpg','cc',1),(7,'tad','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','tad','https://cdn.quasar.dev/img/avatar5.jpg','cc',1),(8,'mary','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','mary','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(9,'aiken','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','aiken','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(10,'jenny','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','jenny','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(11,'june','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','june','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(12,'july','$2a$10$/rpTPcUg87UhWGb5lRwSNe2qFZHTOY7s6McBwQdou.8DQlIxjlWlq','july','https://cdn.quasar.dev/img/avatar5.jpg','cc',0),(28,'test','$2a$10$Ad0LanRNHLwGW4SOYvnT4uOfr6R0fxxg267BnPeetvwR.jrmIc8zy','test',NULL,'657657@qq.com',1);
/*!40000 ALTER TABLE `user_zkm` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-22  8:52:51
