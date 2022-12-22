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
INSERT INTO `account` VALUES (1,'2022-12-20 18:08:30','2022-12-21 17:37:03','BigCorp','$2a$08$LZekqqy1aWu8Kad7DL0QwuRY8QN53J/50Rf5ezI.TINVopEtjj5TW','bigcorp@gmail.com',1,'Big Corporation','144 Xuân Thủy, Cầu Giấy, Hà Nội','0128888888'),(2,'2022-12-20 18:44:17','2022-12-20 18:44:17','nhamay01','$2a$08$DuTf.Q.uNb1B1BI1HHmKJuGhAGlmyDuNyMpXaxq5KdBO9S7yauWxO','nhamay1@gmail.com',2,'Nhà Máy 1','145 Xuân Thủy, Cầu Giấy, Hà Nội','0123456789'),(4,'2022-12-20 18:46:32','2022-12-20 18:46:32','baohanh01','$2a$08$2WV5qEiAkXq65xibNCa0Q.YAtb4BdjvcnNVQzVMscOV1UdH4O6oDm','baohanh01@gmail.com',3,'Bảo Hành 1','146 Xuân Thủy, Cầu Giấy, Hà Nội','0123456788'),(5,'2022-12-20 18:51:27','2022-12-20 18:51:27','daily01','$2a$08$A8lRtVuCcAYdVSJJ2RNLlOptQAp/.86gDst2NBdEQeU5tYlRDPBKy','daily01@gmail.com',4,'Đại lý 1','147 Xuân Thủy, Cầu Giấy, Hà Nội','0123456787'),(6,'2022-12-20 22:16:43','2022-12-20 22:16:43','nhamay02','$2a$08$YAJhZ/Tb1L5wHUk9vS76g.IRFoBMZaWSmNK2tJGcfIihcKcssoO5q','nhamay02@gmail.com',2,'Nhà máy 2','148 Xuân Thủy, Cầu Giấy, Hà Nội','0123456786'),(7,'2022-12-20 22:17:33','2022-12-20 22:17:33','nhamay03','$2a$08$PLnrwS8pqiLHuNA2bwQEwullyt5koMO27QRg0N2lATUjcLCzeh7PS','nhamay03@gmail.com',2,'Nhà máy 3','149 Xuân Thủy, Cầu Giấy, Hà Nội','0123456785'),(8,'2022-12-20 22:20:06','2022-12-20 22:20:06','baohanh02','$2a$08$2FAXHNynPAJ.pM08uQ6AIO/tcCMVi8dJu1IRSk2g1B9lcooTIaqEa','baohanh02@gmail.com',3,'Bảo hành 2','150 Xuân Thủy, Cầu Giấy, Hà Nội','0123456784'),(9,'2022-12-20 22:20:24','2022-12-20 22:20:24','baohanh03','$2a$08$6jI9B7EyTYO6qWeL4ifHnu7ZuBQEk0v.BVfJlVy8yh80SSo1460RW','baohanh03@gmail.com',3,'Bảo hành 3','151 Xuân Thủy, Cầu Giấy, Hà Nội','0123456783'),(10,'2022-12-20 22:22:11','2022-12-20 22:22:11','daily02','$2a$08$RXMGkJo/NU18.Rhk5riK.OXk4hLhfYAjkB5YhocEZ0DlWpoCaiGZG','daily02@gmail.com',4,'Đại lý 2','152 Xuân Thủy, Cầu Giấy, Hà Nội','0123456782'),(11,'2022-12-20 22:22:28','2022-12-20 22:22:28','daily03','$2a$08$ohiZ5w.wWTk2IE8w3hnRFeGw2znQkW.npTJXmfj1saSdOWAE2bKLq','daily03@gmail.com',4,'Đại lý 3','153 Xuân Thủy, Cầu Giấy, Hà Nội','0123456781');
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
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
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
  CONSTRAINT `operation1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation2` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation3` FOREIGN KEY (`company_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (1,'2022-12-22 19:10:18','2022-12-22 19:10:18',1,1,2,NULL),(2,'2022-12-22 19:11:53','2022-12-22 19:11:53',2,1,6,NULL),(3,'2022-12-22 21:31:56','2022-12-22 21:31:56',3,1,6,NULL),(4,'2022-12-22 21:57:17','2022-12-22 21:57:17',4,1,6,NULL),(5,'2022-12-22 21:59:15','2022-12-22 21:59:15',5,1,7,NULL);
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
  `customer_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_category_fk` (`category_id`),
  KEY `product_customer_fk` (`customer_id`),
  CONSTRAINT `product_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2022-12-22 19:10:18','2022-12-22 19:10:18','b1584ae448f347c685611d986e972fdc','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',1,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,NULL,2),(2,'2022-12-22 19:11:53','2022-12-22 19:11:53','e9c36a580be6440193ec3a0fd959a869','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',1,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,NULL,6),(3,'2022-12-22 21:31:56','2022-12-22 21:31:56','b7ef6c9573e44940939cd651e92f5884','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',3,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,NULL,6),(4,'2022-12-22 21:57:17','2022-12-22 21:57:17','483b8b0881274fbb9431e565e608d11a','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',3,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,NULL,6),(5,'2022-12-22 21:59:15','2022-12-22 21:59:15','ba80e6dcd8394e65b7ffc7723760b046','Laptop ProductionMove Aspire 3 A314 35 P6JF N6000/4GB/512GB/Win10',5,'9.590.000','{\'CPU:\': \'Pentium\', \'RAM:\': \'4 GB\', \'Ổ cứng:\': \'512 GB SSD NVMe PCIe\', \'Màn hình:\': \'14\', \'Card màn hình:\': \'Card tích hợp\', \'Cổng kết nối:\': \'2 x USB 3.2\', \'Hệ điều hành:\': \'Windows 10 Home SL\', \'Thiết kế:\': \'Vỏ nhựa\', \'Kích thước, trọng lượng:\': \'Dài 328 mm - Rộng 236 mm - Dày 19.9 mm - Nặng 1.45 kg\', \'Thời điểm ra mắt:\': \'2020\'}',1,NULL,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'2022-12-20 16:46:32','2022-12-20 16:46:32','Mới sản xuất'),(2,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đưa về đại lý'),(3,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đã bán'),(4,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi, cần bảo hành'),(5,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đang sửa chữa bảo hành'),(6,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đã bảo hành xong'),(7,'2022-12-20 17:43:33','2022-12-20 17:43:33','Đã trả lại bảo hành cho khách hàng'),(8,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi, cần trả về nhà máy'),(9,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi, đã đưa về cơ sở sản xuất'),(10,'2022-12-20 17:43:33','2022-12-20 17:43:33','Lỗi cần triệu hồi'),(11,'2022-12-20 17:43:33','2022-12-20 17:43:33','Hết thời gian bảo hành'),(12,'2022-12-20 17:43:33','2022-12-20 17:43:33','Trả lại cơ sở sản xuất');
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

-- Dump completed on 2022-12-22 22:16:06
