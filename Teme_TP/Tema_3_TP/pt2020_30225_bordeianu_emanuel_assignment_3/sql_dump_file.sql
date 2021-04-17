-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: temattp
-- ------------------------------------------------------
-- Server version	5.6.45-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientts`
--

DROP TABLE IF EXISTS `clientts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientts` (
  `nume_client` varchar(30) NOT NULL,
  `adresa` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`nume_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientts`
--

LOCK TABLES `clientts` WRITE;
/*!40000 ALTER TABLE `clientts` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine_comenzi`
--

DROP TABLE IF EXISTS `ordine_comenzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine_comenzi` (
  `nume_client_ordine` varchar(30) DEFAULT NULL,
  `nume_produs_ordine` varchar(30) DEFAULT NULL,
  `cantitate_produs_ordine` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine_comenzi`
--

LOCK TABLES `ordine_comenzi` WRITE;
/*!40000 ALTER TABLE `ordine_comenzi` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordine_comenzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produse`
--

DROP TABLE IF EXISTS `produse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produse` (
  `nume_produs` varchar(30) DEFAULT NULL,
  `cantitate_produs` int(11) NOT NULL,
  `pret_produs` double DEFAULT NULL,
  PRIMARY KEY (`cantitate_produs`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produse`
--

LOCK TABLES `produse` WRITE;
/*!40000 ALTER TABLE `produse` DISABLE KEYS */;
/*!40000 ALTER TABLE `produse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `id_warehouse` int(11) NOT NULL AUTO_INCREMENT,
  `nume_client_warehouse` varchar(30) DEFAULT NULL,
  `nume_produs_warehouse` varchar(30) DEFAULT NULL,
  `cantitate_produs_warehouse` int(11) DEFAULT NULL,
  `pret_produs_warehouse` double DEFAULT NULL,
  PRIMARY KEY (`id_warehouse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-15 21:22:41
