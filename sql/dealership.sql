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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'tai','taibui97@outlook.com','6579668339'),(2,'Tai','tai@example.com','6579668339'),(3,'Linh','linh@gmail.com','4081234567'),(4,'Alex','alex@domain.com','3109876543'),(5,'Maria','maria@company.com','9162345678'),(6,'John','john@yahoo.com','7023456789');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newvehicles`
--

DROP TABLE IF EXISTS `newvehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newvehicles` (
  `warrantyPeriod` double DEFAULT NULL,
  `manufactureDIscount` double DEFAULT NULL,
  `vehicles_VIN` varchar(20) NOT NULL,
  KEY `fk_newvehicles_vehicles1_idx` (`vehicles_VIN`),
  CONSTRAINT `fk_newvehicles_vehicles1` FOREIGN KEY (`vehicles_VIN`) REFERENCES `vehicles` (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newvehicles`
--

LOCK TABLES `newvehicles` WRITE;
/*!40000 ALTER TABLE `newvehicles` DISABLE KEYS */;
/*!40000 ALTER TABLE `newvehicles` ENABLE KEYS */;
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
  PRIMARY KEY (`saleID`),
  KEY `fk_sales_customers_idx` (`customers_customerID`),
  KEY `fk_sales_employees1_idx` (`employees_employeeID`),
  CONSTRAINT `fk_sales_customers` FOREIGN KEY (`customers_customerID`) REFERENCES `customers` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice','casdca@asfdcas.com',NULL,'employee'),(2,'dasda','dasd@sdfsdfsd','sdfsdf','employee'),(3,'asd','asdas','dasd','employee'),(4,'asd','asdas','dasd','employee'),(5,'admin','admin@dealership.com','123456','admin');
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
  `inventories_inventoryID` int NOT NULL,
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
INSERT INTO `vehicles` VALUES ('DZD8P0RR75HS3BVJG','Toyota','Camry',2020,24000,'Available','New',NULL,0),('ERXGHWGEZ4F32W54J','Ford','Focus',2021,21000,'Available','New',NULL,0),('P1174T147UEZC8FB0','Honda','Civic',2019,22000,'Sold','Used',NULL,0),('V1M27SX4Z80LVTCBH','Nissan','Altima',2022,25000,'Available','New',NULL,0),('Z3ZEVGY9SD41BW29A','Chevrolet','Impala',2018,23000,'In Maintenance','Used',NULL,0);
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

-- Dump completed on 2025-04-25 14:36:23
