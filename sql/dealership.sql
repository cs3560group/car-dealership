-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: dealership
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'tai','taibui97@outlook.com','6579668339'),(2,'Tai','tai@example.com','6579668339'),(3,'Linh','linh@gmail.com','4081234567'),(4,'Alex','alex@domain.com','3109876543'),(5,'Maria','maria@company.com','9162345678'),(6,'John','john@yahoo.com','7023456789'),(7,'afa','asda@asca.com','1234124124'),(8,'Tai','daasd','12345');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employeeID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `position` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'john','Saleman','jasdiaj@ac.com');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `saleID` int NOT NULL AUTO_INCREMENT,
  `saleDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `salePrice` double NOT NULL,
  `paymentMethod` varchar(20) DEFAULT NULL,
  `customers_customerID` int NOT NULL,
  `employees_employeeID` int NOT NULL,
  `VIN` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`saleID`),
  KEY `fk_sales_customers_idx` (`customers_customerID`),
  KEY `fk_sales_employees1_idx` (`employees_employeeID`),
  KEY `fk_sales_vehicle` (`VIN`),
  CONSTRAINT `fk_sales_customers` FOREIGN KEY (`customers_customerID`) REFERENCES `customers` (`customerID`),
  CONSTRAINT `fk_sales_vehicle` FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (3,'2025-05-05 11:42:49',18500,'Credit Card',1,1,'1231'),(4,'2025-05-05 12:32:02',15000,'Cash',1,1,'ERXGHWGEZ4F32W54J'),(5,'2025-05-05 13:31:46',15000,'cash',7,2,'Z3ZEVGY9SD41BW29A'),(6,'2025-05-05 13:35:54',50000,'Cash',8,5,'Z3ZEVGY9SD41BW29A');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `supplierID` int NOT NULL AUTO_INCREMENT,
  `companyName` varchar(100) DEFAULT NULL,
  `contactNumber` varchar(100) DEFAULT NULL,
  `contactEmail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usedvehicles`
--

DROP TABLE IF EXISTS `usedvehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usedvehicles` (
  `mileage` double DEFAULT NULL,
  `previousOwners` int DEFAULT NULL,
  `vehicles_VIN` varchar(20) NOT NULL,
  KEY `fk_usedvehicles_vehicles1_idx` (`vehicles_VIN`),
  CONSTRAINT `fk_usedvehicles_vehicles1` FOREIGN KEY (`vehicles_VIN`) REFERENCES `vehicles` (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usedvehicles`
--

LOCK TABLES `usedvehicles` WRITE;
/*!40000 ALTER TABLE `usedvehicles` DISABLE KEYS */;
/*!40000 ALTER TABLE `usedvehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` enum('admin','employee','manager','customer') NOT NULL DEFAULT 'employee',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'dasda','dasd@sdfsdfsd','sdfsdf','employee'),(3,'asd','asdas','dasd','employee'),(4,'asd','asdas','dasd','employee'),(5,'admin','admin@dealership.com','123456','admin'),(6,'asdasdas','scasdca','scasd','employee'),(7,'asdasd','asda','asdasd','manager'),(8,'5','123','12312','admin'),(9,'qwedqw','dqwd','qwdqwd','admin'),(10,'qwdqwdasdas','dasdasdas','dasdas','admin'),(11,'5','1231','23123','admin'),(12,'1','1','abc','manager'),(13,'12312312','3123','1231231','employee'),(14,'asda','sdasd','asdasd','admin'),(15,'12sada','sdas','dasd','employee'),(17,'admin','admin','admin','admin'),(18,'manager','manager','manager','manager'),(19,'employee','employee@dealership.com','employee','employee');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `VIN` varchar(20) NOT NULL,
  `make` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `condition` varchar(20) DEFAULT NULL,
  `sales_saleID` int DEFAULT NULL,
  `inventories_inventoryID` int DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VIN`),
  KEY `fk_vehicles_sales1_idx` (`sales_saleID`),
  KEY `fk_vehicles_inventories1_idx` (`inventories_inventoryID`),
  CONSTRAINT `fk_vehicles_sales1` FOREIGN KEY (`sales_saleID`) REFERENCES `sales` (`saleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES ('1231','1231','12312',123123,12312,'312312','31231',NULL,NULL,'2018_Ford_Focus_ST-Line_X_1.0.jpg'),('asd','asd','asdas',12,12,'das','dasd',NULL,NULL,'2019_Toyota_Corolla_Icon_Tech_VVT-i_Hybrid_1.8.jpg'),('DZD8P0RR75HS3BVJG','Toyota','Camry',2020,12000,'Available','New',NULL,0,'19_Civic_HB_Sport_White.png'),('ERXGHWGEZ4F32W54J','Ford','Focus',2021,21000,'Available','New',NULL,0,NULL),('P1174T147UEZC8FB0','Honda','Civic',2019,22000,'Sold','Used',NULL,0,'19_Civic_HB_Sport_White.png'),('V1M27SX4Z80LVTCBH','Nissan','Altima',2022,25000,'Available','New',NULL,0,'2019_Toyota_Corolla_Icon_Tech_VVT-i_Hybrid_1.8.jpg'),('Z3ZEVGY9SD41BW29A','Chevrolet','Impala',2018,23000,'In Maintenance','Used',NULL,0,'2018_Ford_Focus_ST-Line_X_1.0.jpg');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-05  6:50:22
