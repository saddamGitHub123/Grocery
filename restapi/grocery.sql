CREATE DATABSE GROCERY;
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));


create database grocery;
DROP TABLE IF EXISTS `address`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: grocery
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `usershop_details`
--

DROP TABLE IF EXISTS `grocery`.`usershop_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery`.`usershop_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `User_ID` varchar(20) DEFAULT NULL,
  `Name` varchar(50) NOT NULL,
  `User_Name` varchar(20) NOT NULL,
  `User_Password` varchar(20) NOT NULL,
  `Phn_Number` varchar(20) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Is_Active` tinyint(1) NOT NULL,
  `Shop_Count` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UC_usershop_details` (`User_Name`,`Phn_Number`,`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usershop_details`
--

LOCK TABLES `grocery`.`usershop_details` WRITE;
/*!40000 ALTER TABLE `grocery`.`usershop_details` DISABLE KEYS */;
INSERT INTO `grocery`.`usershop_details` VALUES (8,'Shop_0',NULL,'ShopKeeper_1','synixia_1','12345321','72044143','sksddmhooan@gmail.com',1,1),(10,'Shop_0','User_0','Grocery_UserU_0','synixia_01','12345321','72044143','sksddmhooan@gmail.com',1,1),(11,'Shop_0','User_1','Grocery_User_0','Grocery_User_1','12314','720141827','sksddmhsaan@gmail.com',1,1);
/*!40000 ALTER TABLE `grocery`.`usershop_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 13:05:28
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: grocery
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `grocery`.`address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery`.`address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) DEFAULT NULL,
  `User_ID` varchar(20) DEFAULT NULL,
  `House_No` varchar(20) DEFAULT NULL,
  `Locality` varchar(50) DEFAULT NULL,
  `Landmark` varchar(50) DEFAULT NULL,
  `PinCode` varchar(20) DEFAULT NULL,
  `Area` varchar(20) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Address_Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_UserAddress` FOREIGN KEY (`ID`) REFERENCES `usershop_details` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `grocery`.`address` WRITE;
/*!40000 ALTER TABLE `grocery`.`address` DISABLE KEYS */;
INSERT INTO `grocery`.`address` VALUES (8,'Shop_0',NULL,'#6, 52th Main','Kamalanaga','Op usstop','713409','Bas1av','BLR15',1),(10,'Shop_0','User_0','#6, 52th Main','Kamalanaga','Op usstop','713409','Bas1av','BLR15',1),(11,'Shop_0','User_1',NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `grocery`.`address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 13:05:28


-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: grocery
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `productlist`
--

DROP TABLE IF EXISTS `grocery`.`productlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery`.`productlist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `Product_ID` varchar(40) NOT NULL,
  `Product_Name` varchar(40) DEFAULT NULL,
  `Product_Image` longblob,
  `Product_Category` varchar(20) DEFAULT NULL,
  `Product_Type` varchar(20) DEFAULT NULL,
  `Availability` tinyint(1) DEFAULT NULL,
  `Product_Active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UC_productlist` (`Product_ID`,`Product_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productlist`
--

LOCK TABLES `grocery`.`productlist` WRITE;
/*!40000 ALTER TABLE `grocery`.`productlist` DISABLE KEYS */;
INSERT INTO `grocery`.`productlist` VALUES (4,'Shop_0','Product_0','Super Sugar',NULL,'Pack_SL','SL',1,1),(5,'Shop_0','Product_1','Sweet',NULL,'Pack_SL','SL',1,1);
/*!40000 ALTER TABLE `grocery`.`productlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 13:05:28


-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: grocery
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `product_prices`
--

DROP TABLE IF EXISTS `grocery`.`product_prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery`.`product_prices` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `Product_ID` varchar(20) NOT NULL,
  `Price` varchar(400) NOT NULL,
  `Qty_Price` varchar(400) DEFAULT NULL,
  `Stock` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_prices`
--

LOCK TABLES `grocery`.`product_prices` WRITE;
/*!40000 ALTER TABLE `grocery`.`product_prices` DISABLE KEYS */;
INSERT INTO `grocery`.`product_prices` VALUES (10,'Shop_0','Product_0','200','220','888'),(11,'Shop_0','Product_0','300','320','550'),(12,'Shop_0','Product_0','400','720','660'),(13,'Shop_0','Product_1','10','12','388'),(14,'Shop_0','Product_1','30','30','300'),(15,'Shop_0','Product_1','7','75','700');
/*!40000 ALTER TABLE `grocery`.`product_prices` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 13:05:28



-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: grocery
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `orderList`
--

DROP TABLE IF EXISTS `grocery`.`orderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery`.`orderList` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `User_ID` varchar(20) NOT NULL,
  `Product_ID` varchar(40) NOT NULL,
  `Product_Name` varchar(40) DEFAULT NULL,
  `Order_ID` varchar(200) NOT NULL,
  `Units` varchar(20) NOT NULL,
  `Qty` varchar(10) NOT NULL,
  `TimeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Price` varchar(200) NOT NULL,
  `Dispatch` tinyint(1) DEFAULT NULL,
  `Total_Amount` double DEFAULT NULL,
  `Order_Active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderList`
--

LOCK TABLES `grocery`.`orderList` WRITE;
/*!40000 ALTER TABLE `grocery`.`orderList` DISABLE KEYS */;
INSERT INTO `grocery`.`orderList` VALUES (1,'Shop_0','User_0','Product_0','sugar','OID D9ECF9C853','2','30','2017-11-23 11:42:27','30',1,90,1),(2,'Shop_0','User_0','Product_1','Rice','OID D9ECF9C853','2','3','2017-11-23 11:42:27','10',0,90,1),(3,'Shop_1','User_0','Product_0','Super Sugar','OID 877CCFC3CF','1','3','2017-11-23 11:42:27','400',1,90,1),(4,'Shop_1','User_0','Product_1','Sweet','OID 877CCFC3CF','2','3','2017-11-23 11:42:27','10',1,90,1),(5,'Shop_0','User_1','Product_0','Super Sugar','OID 40D603BCB7','3','3','2017-11-23 11:42:27','400',1,90,1),(6,'Shop_0','User_1','Product_1','Sweet','OID 40D603BCB7','5','3','2017-11-23 11:42:27','10',1,90,1),(7,'Shop_0','User_1','Product_0','Super Sugar','OID 8486300756','3','3','2017-11-23 11:42:27','400',0,90,1),(8,'Shop_0','User_1','Product_1','Sweet','OID 8486300756','5','3','2017-11-23 11:42:27','10',0,90,1),(9,'Shop_0','User_0','Product_0','Super Sugar','OID 5D2293701E','2','3','2017-11-23 11:42:27','200',0,90,1),(10,'Shop_0','User_0','Product_1','Sweet','OID 5D2293701E','2','3','2017-11-23 11:42:27','10',1,90,1);
/*!40000 ALTER TABLE `grocery`.`orderList` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 13:05:28




















DROP TABLE IF EXISTS `productlist`;
CREATE TABLE `productlist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `Product_ID` varchar(40) NOT NULL,
  `Product_Name` varchar(40) DEFAULT NULL,
  `Product_Image` longblob,
  `Product_Category` varchar(20) DEFAULT NULL,
  `Product_Type` varchar(20) DEFAULT NULL,
  `Availability` tinyint(1) DEFAULT NULL,
  `Product_Active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT UC_productlist UNIQUE (Product_ID,Product_Name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_prices`;
CREATE TABLE product_prices (
	
	ID INT NOT NULL AUTO_INCREMENT,
	Shop_ID VARCHAR(20)NOT NULL,
    Product_ID VARCHAR(20)NOT NULL,
	Price VARCHAR(400) NOT NULL,
    Qty_Price VARCHAR(400),
    Stock VARCHAR(400),	
	PRIMARY KEY (`ID`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



/**
 * for the orderlist table
 * **/
DROP TABLE IF EXISTS `orderList`;
CREATE TABLE `orderList` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `User_ID` VARCHAR (20) NOT NULL,
  `Product_ID` varchar(40) NOT NULL,
  `Product_Name` varchar(40) DEFAULT NULL,
  `Order_ID` varchar(200) NOT NULL,
  `Units`   VARCHAR(20) NOT NULL,
  `Qty` VARCHAR(10) NOT NULL,
  `TimeStamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Price` VARCHAR(200) NOT NULL,
  `Dispatch` tinyint(1) DEFAULT NULL,
  `Total_Amount` double DEFAULT NULL,
  `Order_Active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;









