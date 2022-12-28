-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: product_move
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role_id` bigint NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `company_name` (`company_name`),
  KEY `user_role_fk` (`role_id`),
  CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'2022-12-20 18:08:30','2022-12-21 17:37:03','BigCorp','$2a$08$LZekqqy1aWu8Kad7DL0QwuRY8QN53J/50Rf5ezI.TINVopEtjj5TW','bigcorp@gmail.com',1,'Big Corporation','144 Xuân Thủy, Cầu Giấy, Hà Nội','0128888888'),(2,'2022-12-20 18:44:17','2022-12-20 18:44:17','nhamay01','$2a$08$DuTf.Q.uNb1B1BI1HHmKJuGhAGlmyDuNyMpXaxq5KdBO9S7yauWxO','nhamay1@gmail.com',2,'Nhà Máy 1','145 Xuân Thủy, Cầu Giấy, Hà Nội','0123456789'),(4,'2022-12-20 18:46:32','2022-12-20 18:46:32','baohanh01','$2a$08$2WV5qEiAkXq65xibNCa0Q.YAtb4BdjvcnNVQzVMscOV1UdH4O6oDm','baohanh01@gmail.com',3,'Bảo Hành 1','146 Xuân Thủy, Cầu Giấy, Hà Nội','0123456788'),(5,'2022-12-20 18:51:27','2022-12-20 18:51:27','daily01','$2a$08$A8lRtVuCcAYdVSJJ2RNLlOptQAp/.86gDst2NBdEQeU5tYlRDPBKy','daily01@gmail.com',4,'Đại lý 1','147 Xuân Thủy, Cầu Giấy, Hà Nội','0123456787'),(6,'2022-12-20 22:16:43','2022-12-26 11:17:01','nhamay02','$2a$08$YAJhZ/Tb1L5wHUk9vS76g.IRFoBMZaWSmNK2tJGcfIihcKcssoO5q','nhamay02@gmail.com',2,'Nhà máy 2','148 Xuân Thủy, Cầu Giấy, Hà Nội','0123456787'),(7,'2022-12-20 22:17:33','2022-12-20 22:17:33','nhamay03','$2a$08$PLnrwS8pqiLHuNA2bwQEwullyt5koMO27QRg0N2lATUjcLCzeh7PS','nhamay03@gmail.com',2,'Nhà máy 3','149 Xuân Thủy, Cầu Giấy, Hà Nội','0123456785'),(8,'2022-12-20 22:20:06','2022-12-20 22:20:06','baohanh02','$2a$08$2FAXHNynPAJ.pM08uQ6AIO/tcCMVi8dJu1IRSk2g1B9lcooTIaqEa','baohanh02@gmail.com',3,'Bảo hành 2','150 Xuân Thủy, Cầu Giấy, Hà Nội','0123456784'),(9,'2022-12-20 22:20:24','2022-12-20 22:20:24','baohanh03','$2a$08$6jI9B7EyTYO6qWeL4ifHnu7ZuBQEk0v.BVfJlVy8yh80SSo1460RW','baohanh03@gmail.com',3,'Bảo hành 3','151 Xuân Thủy, Cầu Giấy, Hà Nội','0123456783'),(10,'2022-12-20 22:22:11','2022-12-20 22:22:11','daily02','$2a$08$RXMGkJo/NU18.Rhk5riK.OXk4hLhfYAjkB5YhocEZ0DlWpoCaiGZG','daily02@gmail.com',4,'Đại lý 2','152 Xuân Thủy, Cầu Giấy, Hà Nội','0123456782'),(11,'2022-12-20 22:22:28','2022-12-20 22:22:28','daily03','$2a$08$ohiZ5w.wWTk2IE8w3hnRFeGw2znQkW.npTJXmfj1saSdOWAE2bKLq','daily03@gmail.com',4,'Đại lý 3','153 Xuân Thủy, Cầu Giấy, Hà Nội','0123456781');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2022-12-21 17:14:13','2022-12-21 17:14:13','Aspire 3'),(2,'2022-12-21 17:14:13','2022-12-21 17:14:13','Aspire 7'),(3,'2022-12-21 17:14:13','2022-12-21 17:14:13','Aspire A315'),(4,'2022-12-21 17:14:13','2022-12-21 17:14:13','Aspire A514'),(5,'2022-12-21 17:14:13','2022-12-21 17:14:13','Nitro 5'),(6,'2022-12-21 17:14:13','2022-12-21 17:14:13','Predator Helios'),(7,'2022-12-21 17:14:13','2022-12-21 17:14:13','Predator Triton'),(8,'2022-12-21 17:14:13','2022-12-21 17:14:13','Swift 3'),(9,'2022-12-21 17:14:13','2022-12-21 17:14:13','Swift 5'),(10,'2022-12-21 17:14:13','2022-12-21 17:14:13','Rog Strix'),(11,'2022-12-21 17:14:13','2022-12-21 17:14:13','Rog Zephyrus'),(12,'2022-12-21 17:14:13','2022-12-21 17:14:13','Tuf Gaming'),(13,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook '),(14,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook A415ea'),(15,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook A515ea'),(16,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook A515ep'),(17,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook Pro'),(18,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook S433ea'),(19,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook Tm420ua'),(20,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook X415ea'),(21,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook X515ea'),(22,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vivobook X515ep'),(23,'2022-12-21 17:14:13','2022-12-21 17:14:13','Zenbook Duo'),(24,'2022-12-21 17:14:13','2022-12-21 17:14:13','Zenbook Flip'),(25,'2022-12-21 17:14:13','2022-12-21 17:14:13','Zenbook Ux325ea'),(26,'2022-12-21 17:14:13','2022-12-21 17:14:13','Zenbook Ux371ea'),(27,'2022-12-21 17:14:13','2022-12-21 17:14:13','Zenbook Ux425ea'),(28,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming G15'),(29,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming G3'),(30,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 14'),(31,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 15'),(32,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 3501'),(33,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 3505'),(34,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 5406'),(35,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 7400'),(36,'2022-12-21 17:14:13','2022-12-21 17:14:13','Inspiron 7501'),(37,'2022-12-21 17:14:13','2022-12-21 17:14:13','Latitude 3520'),(38,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 3400'),(39,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 3405'),(40,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 3500'),(41,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 5402'),(42,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 5410'),(43,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 5415'),(44,'2022-12-21 17:14:13','2022-12-21 17:14:13','Vostro 5510'),(45,'2022-12-21 17:14:13','2022-12-21 17:14:13','Xps 13'),(46,'2022-12-21 17:14:13','2022-12-21 17:14:13','15s Fq2556tu'),(47,'2022-12-21 17:14:13','2022-12-21 17:14:13','15s Fq2559tu'),(48,'2022-12-21 17:14:13','2022-12-21 17:14:13','240 G8'),(49,'2022-12-21 17:14:13','2022-12-21 17:14:13','340s G7'),(50,'2022-12-21 17:14:13','2022-12-21 17:14:13','Elitebook X360'),(51,'2022-12-21 17:14:13','2022-12-21 17:14:13','Envy 13'),(52,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Victus'),(53,'2022-12-21 17:14:13','2022-12-21 17:14:13','Omen 15'),(54,'2022-12-21 17:14:13','2022-12-21 17:14:13','Pavilion 14'),(55,'2022-12-21 17:14:13','2022-12-21 17:14:13','Pavilion 15'),(56,'2022-12-21 17:14:13','2022-12-21 17:14:13','Pavilion Gaming'),(57,'2022-12-21 17:14:13','2022-12-21 17:14:13','Zbook Firefly'),(58,'2022-12-21 17:14:13','2022-12-21 17:14:13','Ideapad 3'),(59,'2022-12-21 17:14:13','2022-12-21 17:14:13','Ideapad 5'),(60,'2022-12-21 17:14:13','2022-12-21 17:14:13','Ideapad Gaming'),(61,'2022-12-21 17:14:13','2022-12-21 17:14:13','Thinkbook 14'),(62,'2022-12-21 17:14:13','2022-12-21 17:14:13','Thinkbook 14s'),(63,'2022-12-21 17:14:13','2022-12-21 17:14:13','Yoga 7'),(64,'2022-12-21 17:14:13','2022-12-21 17:14:13','Yoga 9'),(65,'2022-12-21 17:14:13','2022-12-21 17:14:13','Yoga Duet'),(66,'2022-12-21 17:14:13','2022-12-21 17:14:13','Yoga Slim'),(67,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gram 14'),(68,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gram 16'),(69,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gram 17'),(70,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Bravo'),(71,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Ge66'),(72,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Gf63'),(73,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Gf65'),(74,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Gs66'),(75,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Leopard'),(76,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Modern'),(77,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Pulse'),(78,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gaming Stealth'),(79,'2022-12-21 17:14:13','2022-12-21 17:14:13','Gf75 Thin'),(80,'2022-12-21 17:14:13','2022-12-21 17:14:13','Katana Gaming'),(81,'2022-12-21 17:14:13','2022-12-21 17:14:13','Katana Gf76'),(82,'2022-12-21 17:14:13','2022-12-21 17:14:13','Modern 14'),(83,'2022-12-21 17:14:13','2022-12-21 17:14:13','Modern 15'),(84,'2022-12-21 17:14:13','2022-12-21 17:14:13','Summit E13'),(85,'2022-12-21 17:14:13','2022-12-21 17:14:13','Summit E16');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `name` text NOT NULL,
  `address` text NOT NULL,
  `phone` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (4,'2022-12-24 13:34:32','2022-12-24 13:34:32','Trần Văn Đức','Hưng Yên','0123456789'),(5,'2022-12-25 02:07:00','2022-12-25 02:07:00','Nguyễn Thái Bình','Hải Phòng','0213456789'),(7,'2022-12-27 10:07:40','2022-12-27 10:07:40','Nguyễn Thái Bình','Hải Phòng','0213456789'),(8,'2022-12-27 23:01:29','2022-12-27 23:01:29','Phạm Nhật Thi','Thái Bình','0213456789'),(9,'2022-12-28 00:51:14','2022-12-28 00:51:14','Nguyễn Tấn Minh','Hà Nội','0213456789'),(10,'2022-12-28 03:55:42','2022-12-28 03:55:42','Nguyễn Tấn Minh','Hà Nội','0213456789');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `product_id` bigint NOT NULL,
  `status_id` bigint NOT NULL,
  `company_id` bigint NOT NULL,
  `destination_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `operation1` (`product_id`),
  KEY `operation2` (`status_id`),
  KEY `operation3` (`company_id`),
  KEY `operation4` (`destination_id`),
  CONSTRAINT `operation1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation2` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation3` FOREIGN KEY (`company_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation4` FOREIGN KEY (`destination_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (2,'2021-02-23 15:21:06','2022-12-23 15:21:06',3,1,7,NULL),(3,'2021-12-23 15:21:07','2022-12-23 15:21:07',4,1,7,NULL),(4,'2022-12-23 15:21:43','2022-12-23 15:21:43',5,1,6,NULL),(5,'2022-12-23 15:21:44','2022-12-23 15:21:44',6,1,6,NULL),(6,'2022-12-23 15:21:44','2022-12-23 15:21:44',7,1,6,NULL),(16,'2022-12-23 16:44:53','2022-12-23 16:44:53',3,2,5,NULL),(17,'2022-12-23 21:12:05','2022-12-23 21:12:05',8,1,6,NULL),(18,'2022-12-23 21:16:59','2022-12-23 21:16:59',9,1,6,NULL),(19,'2022-12-24 13:24:19','2022-12-24 13:24:19',10,1,6,NULL),(20,'2021-06-24 13:34:32','2022-12-24 13:34:32',3,3,5,NULL),(21,'2022-12-24 13:37:36','2022-12-24 13:37:36',5,2,5,NULL),(22,'2022-12-24 13:37:36','2022-12-24 13:37:36',7,2,5,NULL),(24,'2022-12-24 15:29:06','2022-12-24 15:29:06',3,4,5,4),(34,'2022-12-24 18:06:15','2022-12-24 18:06:15',10,13,5,6),(35,'2022-12-24 18:12:20','2022-12-24 18:12:20',10,2,5,NULL),(40,'2022-12-24 21:35:48','2022-12-24 21:35:48',3,5,4,5),(41,'2022-12-24 21:42:26','2022-12-24 21:42:26',3,6,5,NULL),(42,'2022-12-24 22:25:47','2022-12-24 22:25:47',3,7,5,NULL),(43,'2021-07-25 02:07:00','2022-12-25 02:07:00',5,3,5,NULL),(44,'2022-12-25 02:13:01','2022-12-25 02:13:01',5,4,5,4),(45,'2022-12-25 02:13:35','2022-12-25 02:13:35',5,5,4,5),(46,'2022-12-25 02:15:09','2022-12-25 02:15:09',3,4,5,4),(47,'2022-12-25 02:15:53','2022-12-25 02:15:53',3,5,4,5),(48,'2022-12-25 02:16:49','2022-12-25 02:16:49',3,6,5,NULL),(49,'2022-12-25 02:18:37','2022-12-25 02:18:37',3,7,5,NULL),(51,'2022-12-25 20:25:00','2022-12-25 20:25:00',5,8,4,7),(52,'2022-12-25 21:22:23','2022-12-25 21:22:23',5,9,7,NULL),(53,'2021-07-26 02:27:00','2022-12-26 02:27:00',7,3,5,NULL),(54,'2022-12-26 03:12:09','2022-12-26 03:12:09',3,10,5,NULL),(55,'2022-12-26 03:12:09','2022-12-26 03:12:09',7,10,5,NULL),(56,'2022-12-26 03:12:09','2022-12-26 03:12:09',10,10,5,NULL),(57,'2022-12-26 03:35:25','2022-12-26 03:35:25',3,4,5,4),(58,'2022-12-26 03:35:25','2022-12-26 03:35:25',7,4,5,4),(59,'2022-12-26 03:35:25','2022-12-26 03:35:25',10,4,5,4),(60,'2022-12-26 03:40:29','2022-12-26 03:40:29',3,5,4,5),(61,'2022-12-26 03:40:29','2022-12-26 03:40:29',7,5,4,5),(62,'2022-12-26 03:41:04','2022-12-26 03:41:04',3,6,5,NULL),(63,'2022-12-26 03:41:04','2022-12-26 03:41:04',7,6,5,NULL),(64,'2022-12-26 03:41:29','2022-12-26 03:41:29',3,7,5,NULL),(65,'2022-12-26 03:41:29','2022-12-26 03:41:29',7,7,5,NULL),(66,'2022-12-26 03:46:07','2022-12-26 03:46:07',10,5,4,5),(67,'2022-12-26 03:47:05','2022-12-26 03:47:05',10,8,4,7),(72,'2022-12-27 10:04:23','2022-12-27 10:04:23',13,1,6,NULL),(73,'2022-12-27 10:05:36','2022-12-27 10:05:36',13,13,5,6),(74,'2022-12-27 10:06:01','2022-12-27 10:06:01',13,2,5,NULL),(75,'2022-12-27 10:06:18','2022-12-27 10:06:18',9,13,5,6),(76,'2022-12-27 10:06:49','2022-12-27 10:06:49',9,2,5,NULL),(77,'2021-11-27 10:07:41','2022-12-27 10:07:41',13,3,5,NULL),(78,'2022-12-27 10:07:48','2022-12-27 10:07:48',13,4,5,4),(79,'2022-12-27 10:08:08','2022-12-27 10:08:08',13,5,4,5),(80,'2022-12-27 10:08:24','2022-12-27 10:08:24',13,8,4,7),(81,'2022-06-27 10:19:31','2022-12-27 10:19:31',9,3,5,NULL),(82,'2022-12-27 10:20:48','2022-12-27 10:20:48',10,9,7,NULL),(83,'2022-12-27 10:20:48','2022-12-27 10:20:48',13,9,7,NULL),(84,'2022-12-27 15:39:30','2022-12-27 15:39:30',14,1,6,NULL),(85,'2022-12-27 15:39:31','2022-12-27 15:39:31',15,1,6,NULL),(86,'2022-12-27 15:39:51','2022-12-27 15:39:51',14,13,5,6),(87,'2022-12-27 15:39:51','2022-12-27 15:39:51',15,13,5,6),(88,'2022-12-27 15:40:07','2022-12-27 15:40:07',14,2,5,NULL),(89,'2022-12-27 15:40:08','2022-12-27 15:40:08',15,2,5,NULL),(90,'2022-12-27 16:18:02','2022-12-27 16:18:02',14,12,6,NULL),(91,'2022-12-27 16:18:02','2022-12-27 16:18:02',15,12,6,NULL),(92,'2022-12-27 22:07:20','2022-12-27 22:07:20',16,1,6,NULL),(93,'2022-12-27 23:00:36','2022-12-27 23:00:36',16,13,5,6),(94,'2022-12-27 23:00:47','2022-12-27 23:00:47',16,2,5,NULL),(95,'2022-06-27 23:01:29','2022-12-27 23:01:29',16,3,5,NULL),(96,'2022-12-27 23:02:22','2022-12-27 23:02:22',16,4,5,4),(97,'2022-12-27 23:03:14','2022-12-27 23:03:14',16,5,4,5),(98,'2022-12-27 23:03:58','2022-12-27 23:03:58',16,6,5,NULL),(99,'2022-12-27 23:04:38','2022-12-27 23:04:38',16,7,5,NULL),(100,'2022-12-27 23:08:34','2022-12-27 23:08:34',16,4,5,4),(101,'2022-12-27 23:08:48','2022-12-27 23:08:48',16,5,4,5),(102,'2022-12-27 23:09:10','2022-12-27 23:09:10',16,8,4,7),(103,'2022-12-27 23:09:33','2022-12-27 23:09:33',16,9,7,NULL),(104,'2022-12-27 23:10:37','2022-12-27 23:10:37',17,1,6,NULL),(105,'2022-12-27 23:10:43','2022-12-27 23:10:43',17,13,5,6),(106,'2022-12-27 23:10:52','2022-12-27 23:10:52',17,2,5,NULL),(107,'2022-12-27 23:11:07','2022-12-27 23:11:07',17,3,5,NULL),(108,'2022-12-27 23:12:20','2022-12-27 23:12:20',9,10,5,NULL),(109,'2022-12-27 23:12:20','2022-12-27 23:12:20',17,10,5,NULL),(110,'2022-12-27 23:12:55','2022-12-27 23:12:55',9,4,5,4),(111,'2022-12-27 23:12:55','2022-12-27 23:12:55',17,4,5,4),(112,'2022-12-27 23:24:03','2022-12-27 23:24:03',14,12,6,NULL),(113,'2022-12-27 23:24:03','2022-12-27 23:24:03',15,12,6,NULL),(114,'2022-12-28 00:49:17','2022-12-28 00:49:17',18,1,6,NULL),(115,'2022-12-28 00:49:58','2022-12-28 00:49:58',18,13,5,6),(116,'2022-12-28 00:50:17','2022-12-28 00:50:17',18,2,5,NULL),(117,'2022-12-28 00:51:14','2022-12-28 00:51:14',18,3,5,NULL),(118,'2022-12-28 00:51:28','2022-12-28 00:51:28',18,4,5,4),(119,'2022-12-28 00:52:02','2022-12-28 00:52:02',18,5,4,5),(120,'2022-12-28 00:52:17','2022-12-28 00:52:17',18,6,5,NULL),(121,'2022-12-28 00:52:31','2022-12-28 00:52:31',18,7,5,NULL),(122,'2022-12-28 00:52:52','2022-12-28 00:52:52',18,4,5,4),(123,'2022-12-28 00:53:02','2022-12-28 00:53:02',18,5,4,5),(124,'2022-12-28 00:53:10','2022-12-28 00:53:10',18,8,4,7),(125,'2022-12-28 00:53:44','2022-12-28 00:53:44',19,1,6,NULL),(126,'2022-12-28 00:53:48','2022-12-28 00:53:48',19,13,5,6),(127,'2022-12-28 00:53:52','2022-12-28 00:53:52',19,2,5,NULL),(128,'2022-12-28 00:54:47','2022-12-28 00:54:47',19,3,5,NULL),(129,'2022-12-28 00:55:03','2022-12-28 00:55:03',18,9,7,NULL),(130,'2022-12-28 00:56:01','2022-12-28 00:56:01',19,10,5,NULL),(131,'2022-12-28 00:56:09','2022-12-28 00:56:09',19,4,5,4),(132,'2022-12-28 00:57:10','2022-12-28 00:57:10',14,12,6,NULL),(133,'2022-12-28 00:57:10','2022-12-28 00:57:10',15,12,6,NULL),(134,'2022-12-28 03:55:05','2022-12-28 03:55:05',20,1,6,NULL),(135,'2022-12-28 03:55:16','2022-12-28 03:55:16',20,13,5,6),(136,'2022-12-28 03:55:19','2022-12-28 03:55:19',20,2,5,NULL),(137,'2022-12-28 03:55:42','2022-12-28 03:55:42',20,3,5,NULL),(138,'2022-12-28 03:55:46','2022-12-28 03:55:46',20,4,5,4),(139,'2022-12-28 03:55:55','2022-12-28 03:55:55',20,5,4,5),(140,'2022-12-28 03:56:00','2022-12-28 03:56:00',20,6,5,NULL),(141,'2022-12-28 03:56:07','2022-12-28 03:56:07',20,7,5,NULL),(142,'2022-12-28 03:56:21','2022-12-28 03:56:21',20,10,5,NULL),(143,'2022-12-28 03:56:31','2022-12-28 03:56:31',20,4,5,4),(144,'2022-12-28 03:58:55','2022-12-28 03:58:55',21,1,6,NULL),(145,'2022-12-28 03:58:56','2022-12-28 03:58:56',22,1,6,NULL),(146,'2022-12-28 03:59:11','2022-12-28 03:59:11',21,13,5,6),(147,'2022-12-28 03:59:11','2022-12-28 03:59:11',22,13,5,6),(148,'2022-12-28 03:59:18','2022-12-28 03:59:18',21,2,5,NULL),(149,'2022-12-28 03:59:18','2022-12-28 03:59:18',22,2,5,NULL),(150,'2022-12-28 03:59:35','2022-12-28 03:59:35',21,10,5,NULL),(151,'2022-12-28 03:59:35','2022-12-28 03:59:35',22,10,5,NULL),(152,'2022-12-28 03:59:41','2022-12-28 03:59:41',21,4,5,4),(153,'2022-12-28 03:59:41','2022-12-28 03:59:41',22,4,5,4);
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `product_code` varchar(50) NOT NULL,
  `product_name` varchar(500) NOT NULL,
  `category_id` bigint NOT NULL,
  `price` varchar(500) NOT NULL,
  `description` text,
  `status_id` bigint NOT NULL,
  `location_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `number_of_warranty` bigint DEFAULT '0',
  `warrant_time` bigint NOT NULL,
  `sales_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_category_fk` (`category_id`),
  KEY `product_customer_fk` (`customer_id`),
  KEY `product_status_fk` (`status_id`),
  KEY `product_account_fk` (`location_id`),
  CONSTRAINT `product_account_fk` FOREIGN KEY (`location_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_status_fk` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'2022-12-23 15:21:06','2022-12-26 03:41:28','PMcb9165f8','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',7,5,4,2,12,'2022-12-24 13:34:32'),(4,'2022-12-23 15:21:07','2022-12-23 15:21:07','PM3907889d','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,7,NULL,0,6,NULL),(5,'2022-12-23 15:21:43','2022-12-26 02:27:00','PM255be7fd','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',9,7,NULL,1,24,'2022-12-25 02:07:00'),(6,'2022-12-23 15:21:43','2022-12-23 15:21:43','PMdf2e53cf','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,6,NULL,0,6,NULL),(7,'2022-12-23 15:21:44','2022-12-27 13:40:00','PM6a08bb32','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',7,5,5,0,12,'2022-12-26 02:26:59'),(8,'2022-12-23 21:12:03','2022-12-23 21:12:03','PM01b7b6c5','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,6,NULL,0,18,NULL),(9,'2022-12-23 21:16:59','2022-12-27 23:12:55','PMa1a7a5a2','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',4,5,7,0,12,'2022-12-27 10:19:31'),(10,'2022-12-24 13:24:19','2022-12-27 10:20:48','PM21052517','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',9,7,NULL,0,12,NULL),(13,'2022-12-27 10:04:23','2022-12-27 12:26:41','PMab2c596c','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',9,7,NULL,1,12,'2022-12-27 10:07:40'),(14,'2022-12-27 15:39:29','2022-12-28 00:57:10','PMbd0a04c2','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',12,6,NULL,0,12,NULL),(15,'2022-12-27 15:39:31','2022-12-28 00:57:10','PMb78908c2','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',12,6,NULL,0,12,NULL),(16,'2022-12-27 22:07:20','2022-12-27 23:11:07','PM5463b1ec','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',9,7,NULL,2,12,'2022-12-27 23:01:29'),(17,'2022-12-27 23:10:37','2022-12-27 23:12:55','PMe8fd52d9','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',4,5,8,0,12,'2022-12-27 23:11:06'),(18,'2022-12-28 00:49:16','2022-12-28 00:55:03','PMc6eb1e5d','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',9,7,NULL,2,12,'2022-12-28 00:51:14'),(19,'2022-12-28 00:53:43','2022-12-28 00:56:09','PMc61ac942','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',4,5,9,0,12,'2022-12-28 00:54:47'),(20,'2022-12-28 03:55:05','2022-12-28 03:56:31','PM811124e1','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',4,5,10,1,12,'2022-12-28 03:55:42'),(21,'2022-12-28 03:58:55','2022-12-28 03:59:41','PMcf052532','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',4,5,NULL,1,12,NULL),(22,'2022-12-28 03:58:56','2022-12-28 03:59:41','PM5c4a0ea8','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',7,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',4,5,NULL,1,12,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2022-12-06 18:00:22','2022-12-06 18:00:22','admin'),(2,'2022-12-06 18:00:22','2022-12-06 18:00:22','factory'),(3,'2022-12-19 12:19:17','2022-12-19 12:19:17','warranty'),(4,'2022-12-06 18:00:22','2022-12-06 18:00:22','agency');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT (now()),
  `last_updated_time` datetime DEFAULT (now()),
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'2022-12-20 16:46:32','2022-12-20 16:46:32','Mới sản xuất'),(2,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đưa về đại lý'),(3,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đã bán'),(4,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi, cần bảo hành'),(5,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đang sửa chữa bảo hành'),(6,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đã bảo hành xong'),(7,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đã trả lại bảo hành cho khách hàng'),(8,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi, cần trả về nhà máy'),(9,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi, đã đưa về cơ sở sản xuất'),(10,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi cần triệu hồi'),(11,'2022-12-20 17:43:33','2022-12-20 17:43:33','Hết thời gian bảo hành'),(12,'2022-12-20 17:43:33','2022-12-20 17:43:33','Trả lại cơ sở sản xuất'),(13,'2022-12-23 00:12:54','2022-12-23 00:12:54','Yêu cầu nhập từ đại lý');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-28  4:01:12
