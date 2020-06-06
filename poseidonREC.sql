-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: poseidon_REC
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Table structure for table `BidList`
--

DROP TABLE IF EXISTS `BidList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BidList` (
  `BidListId` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `bidQuantity` double DEFAULT NULL,
  `askQuantity` double DEFAULT NULL,
  `bid` double DEFAULT NULL,
  `ask` double DEFAULT NULL,
  `benchmark` varchar(45) DEFAULT NULL,
  `bidListDate` timestamp NULL DEFAULT NULL,
  `commentary` varchar(45) DEFAULT NULL,
  `security` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `trader` varchar(45) DEFAULT NULL,
  `book` varchar(45) DEFAULT NULL,
  `creationName` varchar(45) DEFAULT NULL,
  `creationDate` timestamp NULL DEFAULT NULL,
  `revisionName` varchar(45) DEFAULT NULL,
  `revisionDate` timestamp NULL DEFAULT NULL,
  `dealName` varchar(45) DEFAULT NULL,
  `dealType` varchar(45) DEFAULT NULL,
  `sourceListId` varchar(45) DEFAULT NULL,
  `side` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`BidListId`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CurvePoint`
--

DROP TABLE IF EXISTS `CurvePoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CurvePoint` (
  `id` int(11) NOT NULL,
  `curveId` int(11) DEFAULT NULL,
  `asOfDate` timestamp NULL DEFAULT NULL,
  `term` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `creationDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rating`
--

DROP TABLE IF EXISTS `Rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rating` (
  `id` int(11) NOT NULL,
  `moodysRating` varchar(45) DEFAULT NULL,
  `sandPRating` varchar(45) DEFAULT NULL,
  `fitchRating` varchar(45) DEFAULT NULL,
  `orderNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rule`
--

DROP TABLE IF EXISTS `Rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rule` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `json` varchar(45) DEFAULT NULL,
  `template` varchar(45) DEFAULT NULL,
  `sqlStr` varchar(45) DEFAULT NULL,
  `sqlPart` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Trade`
--

DROP TABLE IF EXISTS `Trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trade` (
  `tradeId` int(11) NOT NULL,
  `account` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `buyQuantity` double DEFAULT NULL,
  `sellQuantity` double DEFAULT NULL,
  `buyPrice` double DEFAULT NULL,
  `sellPrice` double DEFAULT NULL,
  `benchmark` varchar(45) DEFAULT NULL,
  `tradeDate` timestamp NULL DEFAULT NULL,
  `security` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `trader` varchar(45) DEFAULT NULL,
  `book` varchar(45) DEFAULT NULL,
  `creationName` varchar(45) DEFAULT NULL,
  `creationDate` timestamp NULL DEFAULT NULL,
  `revisionName` varchar(45) DEFAULT NULL,
  `revisionDate` timestamp NULL DEFAULT NULL,
  `dealName` varchar(45) DEFAULT NULL,
  `dealType` varchar(45) DEFAULT NULL,
  `sourceListId` varchar(45) DEFAULT NULL,
  `side` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tradeId`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-06  9:58:04
