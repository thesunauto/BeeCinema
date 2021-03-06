CREATE DATABASE  IF NOT EXISTS `pylvrzw8tvy4qa2m` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pylvrzw8tvy4qa2m`;
-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: pylvrzw8tvy4qa2m
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dayghe`
--

DROP TABLE IF EXISTS `dayghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dayghe` (
  `id` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `gia` float NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dayghe`
--

LOCK TABLES `dayghe` WRITE;
/*!40000 ALTER TABLE `dayghe` DISABLE KEYS */;
INSERT INTO `dayghe` VALUES ('1','A',10000,'2020-10-24 17:33:32',0),('11','L',0,'2020-10-24 17:33:32',0),('2','B',0,'2020-10-24 17:33:32',0),('3','C',0,'2020-10-24 17:33:32',0),('4','D',0,'2020-10-24 17:33:32',0),('5','E',0,'2020-10-24 17:33:32',0),('6','F',0,'2020-10-24 17:33:32',0),('7','G',0,'2020-10-24 17:33:32',0),('8','H',0,'2020-10-24 17:33:32',0),('9','J',0,'2020-10-24 17:33:32',0);
/*!40000 ALTER TABLE `dayghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dotuoi`
--

DROP TABLE IF EXISTS `dotuoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dotuoi` (
  `id` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `mota` varchar(450) DEFAULT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idnhanvien_dotuoi_username_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_idnhanvien_dotuoi_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dotuoi`
--

LOCK TABLES `dotuoi` WRITE;
/*!40000 ALTER TABLE `dotuoi` DISABLE KEYS */;
INSERT INTO `dotuoi` VALUES ('C16','C16 - PHIM CẤM KHÁN GIẢ DƯỚI 16 TUỔI','trẻ dưới 16 tuổi không được xem phim này','chaund','2020-10-21 09:25:00',2),('C18','C18 - PHIM CẤM KHÁN GIẢ DƯỚI 18 TUỔI','Người xem phim này phải trên 18 tuổi','chaund','2020-10-21 09:25:00',2),('P','P - PHIM DÀNH CHO MỌI ĐỐI TƯỢNG','Ai cũng có thể xem những bộ phim mang nhãn P này','chaund','2020-10-21 09:25:00',2);
/*!40000 ALTER TABLE `dotuoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ghe`
--

DROP TABLE IF EXISTS `ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ghe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idphong` varchar(45) NOT NULL,
  `iddayghe` varchar(45) NOT NULL,
  `col` int(11) NOT NULL,
  `idloaighe` varchar(45) NOT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idphong_ghe_id_phong` (`idphong`),
  KEY `fk_idloaighe_ghe_id_loaighe` (`idloaighe`),
  KEY `fk_iddayghe_ghe_id_dayghe` (`iddayghe`),
  KEY `fk_idnhanvien_ghe_username_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_iddayghe_ghe_id_dayghe` FOREIGN KEY (`iddayghe`) REFERENCES `dayghe` (`id`),
  CONSTRAINT `fk_idloaighe_ghe_id_loaighe` FOREIGN KEY (`idloaighe`) REFERENCES `loaighe` (`id`),
  CONSTRAINT `fk_idnhanvien_ghe_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`),
  CONSTRAINT `fk_idphong_ghe_id_phong` FOREIGN KEY (`idphong`) REFERENCES `phong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghe`
--

LOCK TABLES `ghe` WRITE;
/*!40000 ALTER TABLE `ghe` DISABLE KEYS */;
INSERT INTO `ghe` VALUES (9,'1','1',1,'2','nhanpt','2020-10-10 10:00:00',0),(10,'1','1',2,'2','nhanpt','2020-10-10 10:00:00',0),(11,'1','1',3,'2','nhanpt','2020-10-10 10:00:00',0),(12,'1','1',4,'2','nhanpt','2020-10-10 10:00:00',0),(13,'1','1',5,'2','nhanpt','2020-10-10 10:00:00',0),(14,'1','1',6,'2','nhanpt','2020-10-10 10:00:00',1),(15,'1','1',7,'2','nhanpt','2020-10-10 10:00:00',0),(16,'1','1',8,'2','nhanpt','2020-10-10 10:00:00',0),(17,'1','1',9,'2','nhanpt','2020-10-10 10:00:00',0),(18,'1','1',10,'2','nhanpt','2020-10-10 10:00:00',0),(19,'1','1',11,'2','nhanpt','2020-10-10 10:00:00',0),(20,'1','2',1,'2','nhanpt','2020-10-10 10:00:00',0),(21,'1','2',2,'2','nhanpt','2020-10-10 10:00:00',0),(22,'1','2',3,'2','nhanpt','2020-10-10 10:00:00',0),(23,'1','2',4,'2','nhanpt','2020-10-10 10:00:00',0),(24,'1','2',5,'2','nhanpt','2020-10-10 10:00:00',0),(25,'1','2',6,'2','nhanpt','2020-10-10 10:00:00',1),(26,'1','2',7,'2','nhanpt','2020-10-10 10:00:00',0),(27,'1','2',8,'2','nhanpt','2020-10-10 10:00:00',0),(28,'1','2',9,'2','nhanpt','2020-10-10 10:00:00',0),(29,'1','2',10,'2','nhanpt','2020-10-10 10:00:00',0),(30,'1','2',11,'2','nhanpt','2020-10-10 10:00:00',0),(31,'1','3',1,'1','nhanpt','2020-10-10 10:00:00',0),(32,'1','3',2,'1','nhanpt','2020-10-10 10:00:00',0),(33,'1','3',3,'1','nhanpt','2020-10-10 10:00:00',0),(34,'1','3',4,'1','nhanpt','2020-10-10 10:00:00',0),(35,'1','3',5,'1','nhanpt','2020-10-10 10:00:00',0),(36,'1','3',6,'1','nhanpt','2020-10-10 10:00:00',1),(37,'1','3',7,'1','nhanpt','2020-10-10 10:00:00',0),(38,'1','3',8,'1','nhanpt','2020-10-10 10:00:00',0),(39,'1','3',9,'1','nhanpt','2020-10-10 10:00:00',0),(40,'1','3',10,'1','nhanpt','2020-10-10 10:00:00',0),(41,'1','3',11,'1','nhanpt','2020-10-10 10:00:00',0),(42,'1','4',1,'1','nhanpt','2020-10-10 10:00:00',0),(43,'1','4',2,'1','nhanpt','2020-10-10 10:00:00',0),(44,'1','4',3,'1','nhanpt','2020-10-10 10:00:00',0),(45,'1','4',4,'1','nhanpt','2020-10-10 10:00:00',0),(46,'1','4',5,'1','nhanpt','2020-10-10 10:00:00',0),(47,'1','4',6,'1','nhanpt','2020-10-10 10:00:00',1),(48,'1','4',7,'1','nhanpt','2020-10-10 10:00:00',0),(49,'1','4',8,'1','nhanpt','2020-10-10 10:00:00',0),(50,'1','4',9,'1','nhanpt','2020-10-10 10:00:00',0),(51,'1','4',10,'1','nhanpt','2020-10-10 10:00:00',0),(52,'1','4',11,'1','nhanpt','2020-10-10 10:00:00',0),(53,'2','4',1,'1','nhanpt','2020-10-10 10:00:00',0),(54,'2','4',2,'1','nhanpt','2020-10-10 10:00:00',0),(55,'2','4',3,'1','nhanpt','2020-10-10 10:00:00',0),(56,'2','4',4,'1','nhanpt','2020-10-10 10:00:00',0),(57,'2','4',5,'1','nhanpt','2020-10-10 10:00:00',0),(58,'2','4',6,'1','nhanpt','2020-10-10 10:00:00',0),(59,'2','4',7,'1','nhanpt','2020-10-10 10:00:00',0),(60,'2','4',8,'1','nhanpt','2020-10-10 10:00:00',0),(61,'2','4',9,'1','nhanpt','2020-10-10 10:00:00',0),(62,'2','4',10,'1','nhanpt','2020-10-10 10:00:00',0),(63,'2','4',11,'1','nhanpt','2020-10-10 10:00:00',0),(64,'2','3',1,'1','nhanpt','2020-10-10 10:00:00',0),(65,'2','3',2,'1','nhanpt','2020-10-10 10:00:00',0),(66,'2','3',3,'1','nhanpt','2020-10-10 10:00:00',0),(67,'2','3',4,'1','nhanpt','2020-10-10 10:00:00',0),(68,'2','3',5,'1','nhanpt','2020-10-10 10:00:00',0),(69,'2','3',6,'1','nhanpt','2020-10-10 10:00:00',0),(70,'2','3',7,'1','nhanpt','2020-10-10 10:00:00',0),(71,'2','3',8,'1','nhanpt','2020-10-10 10:00:00',0),(72,'2','3',9,'1','nhanpt','2020-10-10 10:00:00',0),(73,'2','3',10,'1','nhanpt','2020-10-10 10:00:00',0),(74,'2','3',11,'1','nhanpt','2020-10-10 10:00:00',0),(75,'2','2',1,'1','nhanpt','2020-10-10 10:00:00',0),(76,'2','2',2,'1','nhanpt','2020-10-10 10:00:00',0),(77,'2','2',3,'1','nhanpt','2020-10-10 10:00:00',0),(78,'2','2',4,'1','nhanpt','2020-10-10 10:00:00',0),(79,'2','2',5,'1','nhanpt','2020-10-10 10:00:00',0),(80,'2','2',6,'1','nhanpt','2020-10-10 10:00:00',0),(81,'2','2',7,'1','nhanpt','2020-10-10 10:00:00',0),(82,'2','2',8,'1','nhanpt','2020-10-10 10:00:00',0),(83,'2','2',9,'1','nhanpt','2020-10-10 10:00:00',0),(84,'2','2',10,'1','nhanpt','2020-10-10 10:00:00',0),(85,'2','2',11,'1','nhanpt','2020-10-10 10:00:00',0),(86,'2','1',1,'1','nhanpt','2020-10-10 10:00:00',0),(87,'2','1',2,'1','nhanpt','2020-10-10 10:00:00',0),(88,'2','1',3,'1','nhanpt','2020-10-10 10:00:00',0),(89,'2','1',4,'1','nhanpt','2020-10-10 10:00:00',0),(90,'2','1',5,'1','nhanpt','2020-10-10 10:00:00',0),(91,'2','1',6,'1','nhanpt','2020-10-10 10:00:00',0),(92,'2','1',7,'1','nhanpt','2020-10-10 10:00:00',0),(93,'2','1',8,'1','nhanpt','2020-10-10 10:00:00',0),(94,'2','1',9,'1','nhanpt','2020-10-10 10:00:00',0),(95,'2','1',10,'1','nhanpt','2020-10-10 10:00:00',0),(96,'2','1',11,'1','nhanpt','2020-10-10 10:00:00',0);
/*!40000 ALTER TABLE `ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khunggio`
--

DROP TABLE IF EXISTS `khunggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khunggio` (
  `id` varchar(45) NOT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `batdau` time NOT NULL,
  `ketthuc` time NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idnhanvien_khunggio_username_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_idnhanvien_khunggio_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khunggio`
--

LOCK TABLES `khunggio` WRITE;
/*!40000 ALTER TABLE `khunggio` DISABLE KEYS */;
INSERT INTO `khunggio` VALUES ('10GIO','chaund','10:00:00','13:00:00',0),('11GIO','chaund','11:00:00','13:00:00',0),('13GIO','chaund','13:00:00','15:00:00',0),('14GIO','chaund','14:00:00','16:00:00',0),('15GIO','chaund','15:00:00','17:00:00',0),('16GIO','chaund','16:00:00','18:00:00',0),('17GIO','chaund','17:00:00','19:00:00',0),('20h','chaund','20:00:00','22:30:00',0),('7GIO','chaund','07:00:00','09:00:00',0),('8GIO','chaund','08:00:00','10:00:00',0),('9GIO','chaund','09:00:00','11:00:00',0);
/*!40000 ALTER TABLE `khunggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaighe`
--

DROP TABLE IF EXISTS `loaighe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaighe` (
  `id` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `gia` float NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaighe`
--

LOCK TABLES `loaighe` WRITE;
/*!40000 ALTER TABLE `loaighe` DISABLE KEYS */;
INSERT INTO `loaighe` VALUES ('1','Ghế thường',0,'2020-10-24 17:11:49',0),('2','Ghế VIP',10000,'2020-10-24 17:11:49',0);
/*!40000 ALTER TABLE `loaighe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaiphim`
--

DROP TABLE IF EXISTS `loaiphim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaiphim` (
  `id` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `mota` varchar(450) DEFAULT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idnhanvien_loaiphim_username_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_idnhanvien_loaiphim_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaiphim`
--

LOCK TABLES `loaiphim` WRITE;
/*!40000 ALTER TABLE `loaiphim` DISABLE KEYS */;
INSERT INTO `loaiphim` VALUES ('10','Phim hoạt hình','Thay vì quay các hình ảnh có sẵn, các cảnh trong phim hoạt hình được thực hiện bằng hình vẽ, trước đây là do họa sĩ vẽ tay còn hiện nay trong nhiều phim công đoạn này được vẽ bằng máy vi tính','anhpnh','2020-10-21 10:03:00',0),('11','Phim ca nhạc','Các nhân vật ít thoại hơn bình thường, thay vào đó là nhiều bài hát do chính các diễn viên thể hiện','anhpnh','2020-10-21 10:03:26',0),('12','Phim hài','Chứa đựng nhiều chi tiết hài hước để gây cười cho người xem','anhpnh','2020-10-21 10:03:26',0),('13','Phim phiêu lưu','Bao gồm các chuyến du hành mạo hiểm chứa đựng nhiều hiểm nguy hoặc may mắn, đôi khi có yếu tố thần thoại.','anhpnh','2020-10-21 10:03:26',0),('15','Phim kiếm hiệp','Phim đặc trưng của châu Á, thường có bối cảnh là thời phong kiến và có rất nhiều cuộc giao tranh bằng vũ khí lạnh (kiếm, đao,...). Nếu có các yếu tố phi thực tế, phim kiếm hiệp còn có thể xếp vào loại phim giả tưởng hoặc phim thần bí.','anhpnh','2020-10-21 10:03:26',0),('16','Phim tài liệu','Phim được quay trực tiếp dựa vào các hình ảnh ngoài thực tế, không có hoặc rất ít các chỉ đạo diễn xuất của đạo diễn. Nếu các sự kiện được mô tả trong phim mới xảy ra có tính chất thời sự cao thì phim sẽ được xếp vào thể loại phim thời sự','anhpnh','2020-10-21 10:03:26',0),('2','Phim lịch sử','Bối cảnh phim là các thời điểm trong quá khứ, thường gắn với các sự kiện lịch sử quan trọng','chaund','2020-10-21 10:03:26',0),('3','Phim chiến tranh','Bối cảnh là các trận chiến và thời gian chiến tranh, đây cũng có thể coi là tiểu thể loại của phim lịch sử nếu các sự kiện chiến tranh là có thật trong quá khứ','chaund','2020-10-21 10:03:26',0),('4','Phim khoa học viễn tưởng','Bối cảnh phim có xuất hiện những công nghệ, kỹ thuật hiện đại chưa hoặc không có thật trong thực tế (như du hành thời gian,...), thời gian của phim thường được đặt ở tương lai','anhpnh','2020-10-21 10:03:26',0),('5','Phim cổ trang',' phản ánh lịch sử, phản ảnh sự thật, nhân vật lịch sử, sự kiện lịch sử như thế nào thì phản ánh như thế, không được bịa ra','anhpnh','2020-10-21 10:03:26',0),('6','Phim hành động','Thường bao gồm sự đối đầu giữa \"cái thiện\" và \"cái ác\" với nhiều cuộc chiến ác liệt bằng tay không hoặc vũ khí, tiết tấu nhanh và kĩ xảo điện ảnh cao.','anhpnh','2020-10-21 10:03:26',0),('7','Phim bí ẩn','Thường là quá trình điều tra về một bí ẩn chưa được khám phá.','anhpnh','2020-10-21 10:03:26',0),('8','Phim kinh dị','Là một thể loại phim với nội dung chính đưa đến cho khán giả những cảm xúc tiêu cực, gợi cho người xem nỗi sợ hãi nguyên thủy nhất thông qua cốt truyện, nội dung phim, những hình ảnh rùng rợn, bí hiểm, ánh sáng mờ ảo, những âm thanh rùng rợn, nhiều cảnh máu me, chết chóc... hay có những cảnh giật mình thông qua các sự kiện hoặc nhân vật có','anhpnh','2020-10-21 10:03:26',0),('9','Phim tâm lý - tình cảm','Tập trung khai thác tình yêu lãng mạn giữa các nhân vật chính','anhpnh','2020-10-21 10:03:00',0),('SPORT','Phim thể thao','phim nhiều năng lượng cho mọi lứa tuổi','chaund','2020-12-23 15:47:09',0);
/*!40000 ALTER TABLE `loaiphim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ngonngu`
--

DROP TABLE IF EXISTS `ngonngu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ngonngu` (
  `id` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `mota` varchar(450) DEFAULT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idnhanvien_quocgia_username_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_idnhanvien_quocgia_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ngonngu`
--

LOCK TABLES `ngonngu` WRITE;
/*!40000 ALTER TABLE `ngonngu` DISABLE KEYS */;
INSERT INTO `ngonngu` VALUES ('1','Tiếng Việt - Phụ đề Tiếng Anh','phim do người Việt sản xuất được phụ đề bằng Tiếng Anh','chaund','2020-10-24 18:16:00',0),('2','Tiếng Anh - Phụ đề Tiếng Việt','phim do người Anh sản xuất phụ đề bằng tiếng Việt','chaund','2020-10-24 18:16:00',0),('3','Tiếng Hàn - Phụ đề Tiếng Việt','phim do người Hàn sản xuất phụ đề bằng tiếng Việt','chaund','2020-10-24 18:16:00',0),('4','Tiếng Trung - Phụ đề Tiếng Việt','phim do người Trung sản xuất phụ đề bằng tiếng Việt','chaund','2020-10-24 18:16:00',0),('5','Lồng tiếng','người Việt lòng tiếng','chaund','2020-10-24 18:16:00',0);
/*!40000 ALTER TABLE `ngonngu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phim`
--

DROP TABLE IF EXISTS `phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phim` (
  `id` varchar(45) NOT NULL,
  `iddotuoi` varchar(45) NOT NULL,
  `idngonngu` varchar(45) NOT NULL,
  `idloaiphim` varchar(45) NOT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ten` varchar(250) NOT NULL,
  `hinhanh` varchar(250) DEFAULT NULL,
  `ngaybatdau` datetime NOT NULL,
  `ngayketthuc` datetime NOT NULL,
  `dodai` int(11) NOT NULL,
  `mota` varchar(450) DEFAULT NULL,
  `trangthai` tinyint(4) NOT NULL,
  `linktrailer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_iddotuoi_phim_id_dotuoi` (`iddotuoi`),
  KEY `fk_idloaiphim_phim_id_loaiphim_idx` (`idloaiphim`),
  KEY `fk_idnhanvien_phim_username_taikhoan` (`idnhanvien`),
  KEY `fk_idngonngu_phim_id_ngonngu` (`idngonngu`),
  CONSTRAINT `fk_iddotuoi_phim_id_dotuoi` FOREIGN KEY (`iddotuoi`) REFERENCES `dotuoi` (`id`),
  CONSTRAINT `fk_idloaiphim_phim_id_loaiphim` FOREIGN KEY (`idloaiphim`) REFERENCES `loaiphim` (`id`),
  CONSTRAINT `fk_idngonngu_phim_id_ngonngu` FOREIGN KEY (`idngonngu`) REFERENCES `ngonngu` (`id`),
  CONSTRAINT `fk_idnhanvien_phim_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
INSERT INTO `phim` VALUES ('1','C18','1','12','chaund','TIỆC TRĂNG MÁU','1.jpg','2020-10-23 00:00:00','2020-12-31 23:59:00',118,'Trong buổi họp mặt của nhóm bạn thân, một thành viên bất ngờ đề xuất trò chơi chia sẻ điện thoại nhằm tăng tinh thần “đoàn kết”. Từ đó, những góc khuất của từng người dần hé lộ và khiến cho mối quan hệ vốn khắng khít của họ bắt đầu lay chuyển.',1,'https://youtube.com/embed/IgRJbN88l7E'),('10','P','2','6','chaund','WONDER WOMAN 1984: NỮ THẦN CHIẾN BINH','ww.jpg','2020-12-18 00:00:00','2020-12-31 23:59:00',151,'Lấy bối cảnh năm 1984, 66 năm sau sự kiện diễn ra Thế Chiến thứ I (1918) ở phần phim đầu tiên, Wonder Woman tái hợp với người yêu tưởng chừng đã qua đời Steve Trevor, đồng thời đương đầu với hai kẻ thù mới là Max Lord và The Cheetah.',0,'https://www.youtube.com/embed/9ihTMGouLms'),('11','C16','3','9','chaund','JOSÉE, NÀNG THƠ CỦA TÔI','jo.jpg','2020-12-18 00:00:00','2020-12-31 23:59:00',117,'Bộ phim là câu chuyện tình nên thơ của cậu sinh viên Young Seok (Nam Joo Hyuk) và Josée (Han Ji Min), một cô gái khuyết tật phải ngồi xe lăn vào mùa đông lạnh lẽo. Cuộc gặp gỡ đã khiến thế giới của Josée thay đổi, những ngày tháng đẹp nhất trong cuộc đời họ bắt đầu. Josée muốn được bước ra thế giới bên ngoài, cùng Young Seok đến nơi thật xa nhưng giữa họ có quá nhiều trở ngại. Liệu Young Seok có bên cạnh Josée đi đến tận cùng?',0,'https://www.youtube.com/embed/Dw6myHkqtcQ'),('12','C18','3','6','chaund','NGHỀ SIÊU KHÓ','nsk.jpg','2020-12-11 00:00:00','2020-12-31 23:59:00',111,'Nhóm điều tra do đội trưởng Ko (Ryu Seung-yong) lãnh đạo đứng trước nguy cơ giải tán nhờ chuỗi “thành tích” thất bại đáng nể. Cơ hội cuối cùng để cứu vớt sự nghiệp của họ chính là phải triệt phá một băng đảng buôn bán ma tuý tầm cỡ quốc tế. Để làm được điều đó, đội trưởng Ko và các thành viên trong nhóm đã cải trang thành những nhân viên bán gà tại một quán ăn ngay đối diện hang ổ của kẻ địch.',0,'https://www.youtube.com/embed/wEunhJFDPuw'),('13','C18','1','4','chaund','CUỘC CHIẾN HỦY DIỆT','cchd.jpg','2020-12-11 00:00:00','2020-12-31 23:59:00',114,'SKYLIN3S - phim hành động giả tưởng, xoay quanh cuộc xâm lăng Trái Đất của một chủng tộc người ngoài hành tinh. 15 năm sau kết thúc của phần hai, một loại virus mới đã xuất hiện và xâm nhập vào những người ngoài hành tinh đang sinh sống trên Trái Đất. Loại virus này khiến những sinh vật từ thân thiện trở nên hung hãn và chống lại con người.',0,'https://www.youtube.com/embed/zeQAQK3g0kw'),('2','C18','2','8','chaund','ĐẠI DỊCH XÁC SỐNG','2.jpg','2020-10-23 00:00:00','2020-12-31 23:59:00',93,'‘Đại Dịch Xác Sống’ là câu chuyện đấu tranh để sinh tồn đầy kịch tính của Aidan (Tyler Posey thủ vai), khi bỗng một ngày những người hàng xóm xung quanh anh bị nhiễm một chủng virus lạ và trở thành xác sống. Bị cô lập trong căn hộ với lượng thức ăn có giới hạn, Aidan phải lựa chọn giữa việc cố thủ tại đây hoặc ra ngoài tìm kiếm sự trợ giúp giữa sự bủa vây của lũ quái vật khát máu.',0,'https://youtube.com/embed/inN_Sa2ERYk'),('3','P','3','12','chaund','CỤC NỢ HÓA CỤC CƯNG','3.jpg','2020-10-09 00:00:00','2020-12-31 23:59:00',113,'Du-seok (Sung Dong Il) và Jong-bae (Kim Hiewon) là hai gã chuyên đòi nợ thuê có máu mặt. Để uy hiếp một con nợ, cả hai đã bắt Seung-yi (Park Soi) - một bé gái 9 tuổi làm vật thế chấp cho số nợ của mẹ cô bé. Mặc dù Seung-yi vốn là \"cục nợ\" Du-seok và Jong-bae không hề mong muốn, cô bé dần trở thành cục cưng yêu quý và cả 3 sống bên nhau như một gia đình.',0,'https://youtube.com/embed/QSVOH26UdMU'),('4','P','3','6','chaund','SÓNG THẦN Ở HAEUNDAE','4.jpg','2020-10-16 00:00:00','2020-12-31 23:59:00',120,'Sóng thần ở Haeundae (Tidal Wave) là một bộ phim thảm họa của Hàn Quốc năm 2009 do Yoon Je-kyoon đạo diễn và có sự tham gia của Sol Kyung-gu, Ha Ji-won, Park Joong-hoon và Uhm Jung-hwa.',0,'https://youtube.com/embed/FwtK6FG_Hvg'),('5','C18','2','8','anhpnh','QUÁI VẬT SĂN ĐÊM','5.jpg','2020-10-16 00:00:00','2020-11-15 23:59:00',114,'Một phi hành gia bị tấn công bởi sinh vật bí ẩn ngoài vũ trụ. Để bảo toàn mạng sống, nhà khoa hoc Tatyana được cử tới với nhiệm vụ cứu chữa và giải đáp sự thật kinh hoàng đang bị che giấu.',0,'https://youtube.com/embed/Oj63U2RyVvE'),('6','C18','2','6','anhpnh','CHIẾN BINH HỒI SINH','6.jpg','2022-10-16 00:00:00','2022-12-31 23:59:00',97,'Chuyện phim ‘Chiến Binh Hồi Sinh’ bắt đầu khi nữ quân nhân Ali trở về quê hương New York để hồi phục những chấn thương trong thời gian phục vụ quân ngũ. Được người thân nhờ làm gác cửa tại một căn biệt thự chứa nhiều tài sản quý giá, Ali vô tình trở thành người bảo vệ nơi này trước kế hoạch tấn công được tính toán kỹ lưỡng của một nhóm trộm chuyên nghiệp.',0,'https://youtube.com/embed/kRgnhrmh0Jg'),('7','C16','2','6','anhpnh','PHI VỤ HOÀN LƯƠNG','7.jpg','2019-10-09 00:00:00','2019-12-31 23:59:00',99,'Một tên cướp ngân hàng chuyên nghiệp đã giải nghệ, chấp nhận nộp mình cho FBI vì anh đã trót yêu một người phụ nữ và muốn sống cuộc sống lương thiện với cô gái đó. Tuy nhiên, anh nhận ra việc tiết lộ khoản tiền anh cướp được từ ngân hàng năm xưa cho các Đặc vụ Liên bang là một sai lầm. Vì các đặc vụ này quan tâm đến khoản tiền đó hơn là lời thú nhận của anh.',0,'https://youtube.com/embed/ijwyhBl15ZA'),('8','P','5','10','anhpnh','TÍ HON HẬU ĐẬU','8.jpg','2020-10-16 00:00:00','2020-12-31 23:59:00',78,'Hai trăm năm trước, những yêu tinh vùng Cologne từng giúp đỡ các người thợ trong vùng vào ban đêm. Thế nhưng, sau khi bị một thợ cắt tóc phản bội, họ đã biến mất. Giờ đây, huyền thoại năm xưa xuất hiện trở lại tại một tiệm bánh.',1,'https://youtube.com/embed/1iroiDe97zg');
/*!40000 ALTER TABLE `phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phong` (
  `id` varchar(45) NOT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idnhanvien_phong_username_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_idnhanvien_phong_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
INSERT INTO `phong` VALUES ('1','chaund','Phòng 1','2020-10-21 10:04:00',0),('2','chaund','Phòng 2','2020-10-21 10:04:57',0),('3','chaund','Phòng 3','2020-10-21 10:04:57',0),('4','anhpnh','Phòng 4','2020-10-21 10:04:57',0),('5','anhpnh','Phòng 5','2020-10-21 10:04:57',0),('6','anhpnh','Phòng 6','2020-10-21 10:04:57',0),('7','anhpnh','Phòng 7','2020-10-21 10:04:57',0),('8','anhpnh','Phòng 8','2020-10-21 10:04:57',0),('9','anhpnh','Phòng 9','2020-10-21 10:04:57',0);
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quyen` (
  `id` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES ('1','ADMIN'),('2','EMP'),('3','USER');
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suatchieu`
--

DROP TABLE IF EXISTS `suatchieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suatchieu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idphim` varchar(45) NOT NULL,
  `idphong` varchar(45) NOT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `idkhunggio` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `ngaychieu` date NOT NULL,
  `dongia` float NOT NULL,
  `phuthuyonline` int(11) NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idphim_suatchieu_id_phim` (`idphim`),
  KEY `fk_idphong_suatchieu_id_phong` (`idphong`),
  KEY `fk_idnhanvien_suatchieu_username_taikhoan` (`idnhanvien`),
  KEY `fk_idkhunggio_suatchieu_id_khunggio` (`idkhunggio`),
  CONSTRAINT `FK7m3ihsc3m9hnb43fqsng7eikg` FOREIGN KEY (`idphim`) REFERENCES `phim` (`id`),
  CONSTRAINT `FKdtefombtoblj2h0pqn9nskkep` FOREIGN KEY (`idphong`) REFERENCES `phong` (`id`),
  CONSTRAINT `FKk7g2hhgej7pv28u6if5a2rjla` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`),
  CONSTRAINT `FKqgl2sb0dlfkrrp4yw820uuj7i` FOREIGN KEY (`idkhunggio`) REFERENCES `khunggio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suatchieu`
--

LOCK TABLES `suatchieu` WRITE;
/*!40000 ALTER TABLE `suatchieu` DISABLE KEYS */;
INSERT INTO `suatchieu` VALUES (22,'4','1','chaund','17GIO','2020-12-23 14:52:47','2020-12-23',50000,10,0);
/*!40000 ALTER TABLE `suatchieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sukien`
--

DROP TABLE IF EXISTS `sukien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sukien` (
  `id` varchar(45) NOT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `ten` varchar(450) NOT NULL,
  `hinhanh` varchar(450) NOT NULL,
  `mota` varchar(450) NOT NULL,
  `giam` float NOT NULL,
  `ngaybatdau` datetime NOT NULL,
  `ngayketthuc` datetime NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idnhanvien_sukien_usename_taikhoan` (`idnhanvien`),
  CONSTRAINT `fk_idnhanvien_sukien_usename_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sukien`
--

LOCK TABLES `sukien` WRITE;
/*!40000 ALTER TABLE `sukien` DISABLE KEYS */;
INSERT INTO `sukien` VALUES ('1','chaund','U22','BT21.jpg','\r\nKhách hàng có độ tuổi từ 12 – 22 nhanh chóng nhập hội CGV U22 để nhận ngay ưu đãi đồng giá vô cùng hấp dẫn:\r\n- 45.000 VNĐ - 55.000 VNĐ / 1 vé 2D (*)\r\n',2000,'2020-10-24 17:52:00','2020-12-31 23:59:00',1),('2','chaund','XEM PHIM XUYÊN ĐÊM','xuyendem.jpg','\nTận hưởng giá vé siêu ưu đãi cho các suất chiếu sau 22:00\n- Đồng giá từ 60.000 VNĐ* – Vé xem phim 2D\n',3000,'2020-10-24 17:52:46','2020-12-31 23:59:59',0),('4','anhpnh','Chủ thẻ Visa mua vé xem phim nay giá chỉ 50K cùng ưu đãi CGV','event2.jpg','Khi mua 2 vé xem phim vào thứ 3 hàng tuần (từ 9h sáng)',5000,'2020-01-24 17:52:46','2020-02-24 17:52:46',0);
/*!40000 ALTER TABLE `sukien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taikhoan` (
  `username` varchar(45) NOT NULL,
  `matkhau` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `gioitinh` tinyint(4) DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `diachi` varchar(450) DEFAULT NULL,
  `sodienthoai` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `idquyen` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  `hinhanh` varchar(450) DEFAULT NULL,
  `mota` varchar(450) DEFAULT NULL,
  `trangthai` tinyint(4) NOT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_idquyen_taikhoan_id_quyen` (`idquyen`),
  CONSTRAINT `fk_idquyen_taikhoan_id_quyen` FOREIGN KEY (`idquyen`) REFERENCES `quyen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('anhpnh','123','Anh',0,'2000-04-20','222 bình tân','0769852087','anhpnhhcm@gmail.com','2','2020-10-21 08:39:00','fb_img_1557689132909.jpg','Thành viên dự án Beecinema\r\n',0,NULL),('chaund','123','Châu',0,'2000-04-21','111 quận 9','123456789','chaundhcm@gmail.com','1','2020-10-21 08:17:40','1.jpg','aaaa',0,NULL),('huunt','123','Nguyễn Tín Hữu',0,'2000-02-02','quận 4','035853728','huunthcm@gmail.com','1','2020-10-21 08:39:00','50542064_2079691345655802_8969741900059770880_o.jpg','Thành viên dự án Beecinema',0,NULL),('khoadna','123','Thầy Khoa',0,'2000-01-06','Quận Gò Vấp','0987654321','thesunautomt1@gmail.com','1','2020-12-23 11:45:00','thaykhoa.jpg','Giảng viên hướng dẫn ',0,NULL),('ngandtk','123','Đặng Thị Kim Ngân',1,'2000-03-03','QUẬN 12, HCM','0987654321','ngandtkps09188@fpt.edu.vn','2','2020-10-21 08:39:00','ngan.jpg','Thành viên dự án beecinema',0,NULL),('nhanpt','29022000','Phạm Thành Nhân',0,'2000-06-13','quận 13','093654534','thesunautomt@gmail.com','3','2020-10-21 08:39:00','nhan.jpg','Thành viên dự an beecinema',0,NULL),('sax1509','29022000','Đặng Trần Thông',0,'2000-06-13','Quận Cam','0932028546','thesunautomt2@gmail.com','3','2020-12-23 14:21:59','bat tay.png','tôi muốn ngon cơm',0,NULL);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve`
--

DROP TABLE IF EXISTS `ve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ve` (
  `idsuatchieu` int(11) NOT NULL,
  `idghe` int(11) NOT NULL,
  `idsukien` varchar(45) DEFAULT NULL,
  `idnhanvien` varchar(45) NOT NULL,
  `trangthai` tinyint(4) NOT NULL,
  `ngaytao` datetime NOT NULL,
  PRIMARY KEY (`idsuatchieu`,`idghe`),
  KEY `fk_idghe_ve_id_sukien` (`idsukien`),
  KEY `fk_idnhanvien_ve_username_taikhoan_idx` (`idnhanvien`),
  KEY `fk_idsuatchieu_ve_id_suatchieu` (`idsuatchieu`),
  KEY `FK_idghe_ve_id_ghe` (`idghe`),
  CONSTRAINT `FK_idghe_ve_id_ghe` FOREIGN KEY (`idghe`) REFERENCES `ghe` (`id`),
  CONSTRAINT `fk_idghe_ve_id_sukien` FOREIGN KEY (`idsukien`) REFERENCES `sukien` (`id`),
  CONSTRAINT `fk_idnhanvien_ve_username_taikhoan` FOREIGN KEY (`idnhanvien`) REFERENCES `taikhoan` (`username`),
  CONSTRAINT `fk_idsuatchieu_ve_id_suatchieu` FOREIGN KEY (`idsuatchieu`) REFERENCES `suatchieu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve`
--

LOCK TABLES `ve` WRITE;
/*!40000 ALTER TABLE `ve` DISABLE KEYS */;
INSERT INTO `ve` VALUES (22,9,NULL,'ngandtk',0,'2020-12-23 15:21:16'),(22,11,NULL,'ngandtk',0,'2020-12-23 15:22:15'),(22,12,NULL,'ngandtk',0,'2020-12-23 15:22:15'),(22,13,NULL,'ngandtk',0,'2020-12-23 15:22:15');
/*!40000 ALTER TABLE `ve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veonline`
--

DROP TABLE IF EXISTS `veonline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veonline` (
  `idsuatchieu` int(11) NOT NULL,
  `idghe` int(11) NOT NULL,
  `idthanhvien` varchar(45) NOT NULL,
  `idsukien` varchar(45) DEFAULT NULL,
  `trangthai` varchar(45) NOT NULL,
  `ngaytao` datetime NOT NULL,
  PRIMARY KEY (`idsuatchieu`,`idghe`),
  KEY `fk_idsukien_veonline_id_sukien` (`idsukien`),
  KEY `fk_idnhanvien_veonline_username_taikhoan` (`idthanhvien`),
  KEY `fk_idsuatchieu_veonline_id_suatchieu` (`idsuatchieu`),
  KEY `FK_idghe_veonline_id_ghe` (`idghe`),
  CONSTRAINT `FK_idghe_veonline_id_ghe` FOREIGN KEY (`idghe`) REFERENCES `ghe` (`id`),
  CONSTRAINT `fk_idnhanvien_veonline_username_taikhoan` FOREIGN KEY (`idthanhvien`) REFERENCES `taikhoan` (`username`),
  CONSTRAINT `fk_idsuatchieu_veonline_id_suatchieu` FOREIGN KEY (`idsuatchieu`) REFERENCES `suatchieu` (`id`),
  CONSTRAINT `fk_idsukien_veonline_id_sukien` FOREIGN KEY (`idsukien`) REFERENCES `sukien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veonline`
--

LOCK TABLES `veonline` WRITE;
/*!40000 ALTER TABLE `veonline` DISABLE KEYS */;
INSERT INTO `veonline` VALUES (22,9,'nhanpt',NULL,'1','2020-12-23 14:53:23'),(22,10,'nhanpt',NULL,'0','2020-12-23 14:53:23'),(22,20,'nhanpt','2','0','2020-12-23 16:23:34');
/*!40000 ALTER TABLE `veonline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pylvrzw8tvy4qa2m'
--

--
-- Dumping routines for database 'pylvrzw8tvy4qa2m'
--
/*!50003 DROP PROCEDURE IF EXISTS `LoopDemo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `LoopDemo`()
BEGIN
	DECLARE x  INT;
    DECLARE i  INT;
       DECLARE daya VARCHAR(255);
       
	SET x = 0;
	
        SET daya = '1';
        SET i = 0;
	loop_label:  LOOP
		IF  x > 80 THEN 
			LEAVE  loop_label;
		END  IF;
            
		SET  x = x + 1;
        SET  i = i + 1;
        IF  x = 20 THEN 
			
            SET daya = '2';
            SET i = 1;
		END  IF;
        IF  x = 40 THEN 
			
            SET daya = '3';
            SET i = 1;
		END  IF;
        IF  x = 60 THEN 
		
            SET daya = '4';
            SET i = 1;
		END  IF;
        
        
        
        
		IF  (x mod 2) THEN
			ITERATE  loop_label;
		ELSE
       
			INSERT INTO `pylvrzw8tvy4qa2m`.`ghe`
(`id`,
`idphong`,
`iddayghe`,
`ten`,
`idloaighe`,
`idnhanvien`,
`ngaytao`,
`trangthai`)
VALUES
(CONVERT(x,char), '1', daya, CONVERT(i,char), '1', 'anhpnh', '2020-10-24 18:36:42', '0');

		END  IF;
        
	END LOOP;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-29 10:26:42
