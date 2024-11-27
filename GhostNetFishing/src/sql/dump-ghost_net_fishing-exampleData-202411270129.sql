/*!40000 ALTER TABLE `GhostNet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GhostNetState`
--

DROP TABLE IF EXISTS `GhostNetState`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GhostNetState` (
  `deleteBy_userId` int(11) DEFAULT NULL,
  `ghostnetStateId` int(11) NOT NULL AUTO_INCREMENT,
  `insertBy_userId` int(11) DEFAULT NULL,
  `deleteDate` datetime(6) DEFAULT NULL,
  `insertDate` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ghostnetStateId`),
  KEY `FK4ur3bgk94sumr64yhhpsgjejc` (`deleteBy_userId`),
  KEY `FKcek39bdfb23uayqfnx8knuqt1` (`insertBy_userId`),
  CONSTRAINT `FK4ur3bgk94sumr64yhhpsgjejc` FOREIGN KEY (`deleteBy_userId`) REFERENCES `Users` (`userId`),
  CONSTRAINT `FKcek39bdfb23uayqfnx8knuqt1` FOREIGN KEY (`insertBy_userId`) REFERENCES `Users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GhostNetState`
--

LOCK TABLES `GhostNetState` WRITE;
/*!40000 ALTER TABLE `GhostNetState` DISABLE KEYS */;
INSERT INTO `GhostNetState` VALUES
(NULL,1,1,NULL,'2024-11-27 00:22:10.000000','Eine meldende Person hat das Geisternetz im System erfasst.','Gemeldet'),
(NULL,2,1,NULL,'2024-11-27 00:22:10.000000','Eine bergende Person hat die Bergung angekündigt.','Bergung bevorstehend'),
(NULL,3,1,NULL,'2024-11-27 00:22:10.000000','Eine bergende Person hat das Geisternetz erfolgreich geborgen.','Geborgen'),
(NULL,4,1,NULL,'2024-11-27 00:22:10.000000','Eine beliebige Person hat festgestellt, dass das Geisternetz am gemeldeten Standort nicht auffindbar ist.','Verschollen');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ghost_net_fishing'
--
