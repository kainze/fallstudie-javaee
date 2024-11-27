/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.5.2-MariaDB, for Linux (x86_64)
--
-- Host: 192.168.241.10    Database: ghost_net_fishing
-- ------------------------------------------------------
-- Server version	11.6.2-MariaDB-ubu2404

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `GhostNet`
--

DROP TABLE IF EXISTS `GhostNet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GhostNet` (
  `deleteBy_userId` int(11) DEFAULT NULL,
  `ghostnetId` int(11) NOT NULL AUTO_INCREMENT,
  `ghostnetState_ghostnetStateId` int(11) DEFAULT NULL,
  `gpsLatitude` double NOT NULL,
  `gpsLongitude` double NOT NULL,
  `insertBy_userId` int(11) DEFAULT NULL,
  `notifier_userId` int(11) DEFAULT NULL,
  `rescuer_userId` int(11) DEFAULT NULL,
  `size` int(11) NOT NULL,
  `deleteDate` datetime(6) DEFAULT NULL,
  `insertDate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`ghostnetId`),
  KEY `FKget8ej958l0i6l7p450nxrhy7` (`deleteBy_userId`),
  KEY `FKkjj2aklnul33u83fpeth6y9sg` (`ghostnetState_ghostnetStateId`),
  KEY `FKifhe2y1nw45v8c6ohie9regfu` (`insertBy_userId`),
  KEY `FK5e0wlu2goueowllrruxeupafi` (`notifier_userId`),
  KEY `FKe4mepcibti5l6771jun8cwbga` (`rescuer_userId`),
  CONSTRAINT `FK5e0wlu2goueowllrruxeupafi` FOREIGN KEY (`notifier_userId`) REFERENCES `Users` (`userId`),
  CONSTRAINT `FKe4mepcibti5l6771jun8cwbga` FOREIGN KEY (`rescuer_userId`) REFERENCES `Users` (`userId`),
  CONSTRAINT `FKget8ej958l0i6l7p450nxrhy7` FOREIGN KEY (`deleteBy_userId`) REFERENCES `Users` (`userId`),
  CONSTRAINT `FKifhe2y1nw45v8c6ohie9regfu` FOREIGN KEY (`insertBy_userId`) REFERENCES `Users` (`userId`),
  CONSTRAINT `FKkjj2aklnul33u83fpeth6y9sg` FOREIGN KEY (`ghostnetState_ghostnetStateId`) REFERENCES `GhostNetState` (`ghostnetStateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GhostNet`
--

LOCK TABLES `GhostNet` WRITE;
/*!40000 ALTER TABLE `GhostNet` DISABLE KEYS */;
/*!40000 ALTER TABLE `GhostNetState` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `deleteBy_userId` int(11) DEFAULT NULL,
  `insertBy_userId` int(11) DEFAULT NULL,
  `rescuer` bit(1) NOT NULL,
  `userDisabled` bit(1) NOT NULL,
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `deleteDate` datetime(6) DEFAULT NULL,
  `insertDate` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FKfrncr53cvlg0dmdh9h7d2fg2` (`deleteBy_userId`),
  KEY `FK827n60xpjm5xvcrh1b66mj032` (`insertBy_userId`),
  CONSTRAINT `FK827n60xpjm5xvcrh1b66mj032` FOREIGN KEY (`insertBy_userId`) REFERENCES `Users` (`userId`),
  CONSTRAINT `FKfrncr53cvlg0dmdh9h7d2fg2` FOREIGN KEY (`deleteBy_userId`) REFERENCES `Users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES
(NULL,NULL,'\0','\0',1,NULL,'2024-11-27 00:22:10.000000','admin@example.com','Admin','User','Qkfc1gQyvIvHUV9MANsYD/A4P2PJ2UJfkEkNScF8xsg=','1234567890','admin'),
(NULL,NULL,'','\0',2,NULL,'2024-11-27 00:22:10.000000','hans.schneider@example.com','Hans','Schneider','7zqHuF9f45GXU5X8BjQ4G+l8unUoqBQvS13zAuUMEZA=','01511234567','rescuer1'),
(NULL,NULL,'','\0',3,NULL,'2024-11-27 00:22:10.000000','friedrich.meier@example.com','Friedrich','Meier','9sBBFxACbU+wREBq48IzywUBcctTqQHW9e/xCI6QPwQ=','01511234568','rescuer2'),
(NULL,NULL,'','\0',4,NULL,'2024-11-27 00:22:10.000000','wolfgang.koch@example.com','Wolfgang','Koch','/qY5SZWNDmrbe3NQRvyGgzC6ux4wg4BV2lZMSb4T4wA=','01511234569','rescuer3'),
(NULL,NULL,'\0','\0',5,NULL,'2024-11-27 00:22:10.000000','sophie.fischer@example.com','Sophie','Fischer','9RPLUi9SzekXRbUl45uP4apJj8Ei7SnLhojbMIfwrWc=','01601234567','notifier1'),
(NULL,NULL,'\0','\0',6,NULL,'2024-11-27 00:22:10.000000','anna.weber@example.com','Anna','Weber','4ImEkXaRuTuy4bxRWaOTz/ftqlZ+jaQTNJb3x3CKcD0=','01601234568','notifier2'),
(NULL,NULL,'\0','\0',7,NULL,'2024-11-27 00:22:10.000000','klara.bauer@example.com','Klara','Bauer','KHDt5+SQOzncmlndsPoAhwOu/ia+3ZOHUnKzplBb2X0=','01601234569','notifier3');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ghost_net_fishing'
--
