-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: projetbd
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.20.04.2

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
-- Table structure for table `Allergene`
--

DROP TABLE IF EXISTS `Allergene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Allergene` (
  `nomAllergene` varchar(20) NOT NULL,
  PRIMARY KEY (`nomAllergene`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Allergene`
--

LOCK TABLES `Allergene` WRITE;
/*!40000 ALTER TABLE `Allergene` DISABLE KEYS */;
INSERT INTO `Allergene` VALUES ('Arachides'),('Avoine'),('Banane'),('Carotte'),('Céleri'),('Chocolat'),('Epinard'),('Fruits à coques'),('Gingembre'),('Lait de vache'),('Lupin'),('Mollusques'),('Moutarde'),('Noix'),('Œufs'),('Oignon'),('Poissons'),('protéines'),('Soja'),('Thon');
/*!40000 ALTER TABLE `Allergene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AllergenePlat`
--

DROP TABLE IF EXISTS `AllergenePlat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AllergenePlat` (
  `idPlat` int NOT NULL,
  `idRest` int NOT NULL,
  `allergene` varchar(20) NOT NULL,
  PRIMARY KEY (`idPlat`,`idRest`,`allergene`),
  KEY `allergene` (`allergene`),
  CONSTRAINT `AllergenePlat_ibfk_1` FOREIGN KEY (`idPlat`, `idRest`) REFERENCES `Plat` (`idPlat`, `idRest`),
  CONSTRAINT `AllergenePlat_ibfk_2` FOREIGN KEY (`allergene`) REFERENCES `Allergene` (`nomAllergene`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AllergenePlat`
--

LOCK TABLES `AllergenePlat` WRITE;
/*!40000 ALTER TABLE `AllergenePlat` DISABLE KEYS */;
INSERT INTO `AllergenePlat` VALUES (4,3,'Arachides'),(3,1,'Banane'),(3,2,'Carotte'),(4,2,'Céleri'),(2,3,'Chocolat'),(2,2,'Epinard'),(1,4,'Fruits à coques'),(4,2,'Fruits à coques'),(2,4,'Gingembre'),(3,2,'Gingembre'),(1,1,'Lait de vache'),(4,1,'Lupin'),(3,3,'Mollusques'),(4,2,'Moutarde'),(3,3,'Noix'),(2,2,'Oignon'),(1,2,'Poissons'),(4,3,'protéines'),(1,1,'Soja'),(4,4,'Soja'),(2,1,'Thon');
/*!40000 ALTER TABLE `AllergenePlat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categorie`
--

DROP TABLE IF EXISTS `Categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categorie` (
  `categorie` varchar(40) NOT NULL,
  PRIMARY KEY (`categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categorie`
--

LOCK TABLES `Categorie` WRITE;
/*!40000 ALTER TABLE `Categorie` DISABLE KEYS */;
INSERT INTO `Categorie` VALUES ('cuisine à la bière'),('cuisine arabe'),('cuisine au beurre'),('cuisine basque'),('cuisine chinoise'),('cuisine dauphinoise'),('cuisine de la mer'),('cuisine des alpes'),('cuisine du nord'),('cuisine française'),('cuisine italienne'),('cuisine méditerranéenne'),('cuisine parisienne'),('cuisine régionale'),('cuisine savoyarde'),('cuisine turque'),('cuisines royales'),('Fast food');
/*!40000 ALTER TABLE `Categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategorieMere`
--

DROP TABLE IF EXISTS `CategorieMere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategorieMere` (
  `categorie` varchar(40) NOT NULL,
  `categorieMere` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`categorie`),
  KEY `categorieMere` (`categorieMere`),
  CONSTRAINT `CategorieMere_ibfk_1` FOREIGN KEY (`categorie`) REFERENCES `Categorie` (`categorie`),
  CONSTRAINT `CategorieMere_ibfk_2` FOREIGN KEY (`categorieMere`) REFERENCES `Categorie` (`categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategorieMere`
--

LOCK TABLES `CategorieMere` WRITE;
/*!40000 ALTER TABLE `CategorieMere` DISABLE KEYS */;
INSERT INTO `CategorieMere` VALUES ('cuisine dauphinoise','cuisine des alpes'),('cuisine savoyarde','cuisine des alpes'),('cuisine à la bière','cuisine du nord'),('cuisine au beurre','cuisine du nord'),('cuisine basque','cuisine du nord'),('cuisine de la mer','cuisine du nord'),('cuisine parisienne','cuisine du nord'),('cuisine du nord','cuisine française'),('cuisine méditerranéenne','cuisine française'),('cuisine régionale','cuisine française'),('cuisines royales','cuisine parisienne'),('cuisine des alpes','cuisine régionale');
/*!40000 ALTER TABLE `CategorieMere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategorieRest`
--

DROP TABLE IF EXISTS `CategorieRest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategorieRest` (
  `idRest` int NOT NULL,
  `categorie` varchar(40) NOT NULL,
  PRIMARY KEY (`idRest`,`categorie`),
  KEY `categorie` (`categorie`),
  CONSTRAINT `CategorieRest_ibfk_1` FOREIGN KEY (`categorie`) REFERENCES `Categorie` (`categorie`),
  CONSTRAINT `CategorieRest_ibfk_2` FOREIGN KEY (`idRest`) REFERENCES `Restaurant` (`idRest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategorieRest`
--

LOCK TABLES `CategorieRest` WRITE;
/*!40000 ALTER TABLE `CategorieRest` DISABLE KEYS */;
INSERT INTO `CategorieRest` VALUES (4,'cuisine à la bière'),(5,'cuisine à la bière'),(6,'cuisine arabe'),(5,'cuisine au beurre'),(5,'cuisine chinoise'),(3,'cuisine de la mer'),(2,'cuisine des alpes'),(4,'cuisine du nord'),(2,'cuisine française'),(4,'cuisine française'),(6,'cuisine française'),(3,'cuisine italienne'),(6,'cuisine méditerranéenne'),(4,'cuisine régionale'),(4,'cuisine savoyarde'),(1,'cuisine turque'),(4,'cuisines royales'),(1,'Fast food'),(2,'Fast food');
/*!40000 ALTER TABLE `CategorieRest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `CategorieRestoAssocieOrdreDecroissant`
--

DROP TABLE IF EXISTS `CategorieRestoAssocieOrdreDecroissant`;
/*!50001 DROP VIEW IF EXISTS `CategorieRestoAssocieOrdreDecroissant`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `CategorieRestoAssocieOrdreDecroissant` AS SELECT 
 1 AS `categorie`,
 1 AS `idRest`,
 1 AS `nomRest`,
 1 AS `noteRest`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Client` (
  `idClient` int NOT NULL,
  `emailClient` varchar(30) DEFAULT NULL,
  `motDePasse` varchar(30) DEFAULT NULL,
  `NomClient` varchar(30) DEFAULT NULL,
  `prenomClient` varchar(30) DEFAULT NULL,
  `addrClient` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE KEY `emailClient` (`emailClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES (1,'mehdi@gmail.com','123456789','Kamoun','Mehdi','2 rue de la liberté, Grenoble'),(2,'iheb@gmail.com','iheb45688','Karoui','Iheb','3 rue de la liberté, Grenoble'),(3,'sami@gmail.com','sami123456789','Trabelsi','Sami','1 rue de la liberté, Grenoble'),(4,'samuel@gmail.com','123456789','Vanie','Samuel','4 rue Stalingrad, Grenoble'),(5,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ComLivraison`
--

DROP TABLE IF EXISTS `ComLivraison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ComLivraison` (
  `idComLivraison` int NOT NULL,
  `adresseLivraison` varchar(100) DEFAULT NULL,
  `textLivreur` varchar(100) DEFAULT NULL,
  `heureLivraison` date DEFAULT NULL,
  PRIMARY KEY (`idComLivraison`),
  CONSTRAINT `ComLivraison_ibfk_1` FOREIGN KEY (`idComLivraison`) REFERENCES `Commande` (`idCommande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ComLivraison`
--

LOCK TABLES `ComLivraison` WRITE;
/*!40000 ALTER TABLE `ComLivraison` DISABLE KEYS */;
INSERT INTO `ComLivraison` VALUES (20,'adresse de livraison donnée par l\'utilisateur','i have ran into some traffic i will be there 5 min late','2022-12-04'),(21,'adresse de livraison donnée par l\'utilisateur','i have ran into some traffic i will be there 5 min late','2022-12-04'),(22,'adresse de livraison donnée par l\'utilisateur','i have ran into some traffic i will be there 5 min late','2022-12-04'),(23,'adresse de livraison donnée par l\'utilisateur','i have ran into some traffic i will be there 5 min late','2022-12-04');
/*!40000 ALTER TABLE `ComLivraison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ComSurPlace`
--

DROP TABLE IF EXISTS `ComSurPlace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ComSurPlace` (
  `idComSurPlace` int NOT NULL,
  `nbrPersonne` int DEFAULT NULL,
  `heureArriveSurPlace` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idComSurPlace`),
  KEY `heureArriveSurPlace` (`heureArriveSurPlace`),
  CONSTRAINT `ComSurPlace_ibfk_1` FOREIGN KEY (`heureArriveSurPlace`) REFERENCES `Horaire` (`horaire`),
  CONSTRAINT `ComSurPlace_ibfk_2` FOREIGN KEY (`idComSurPlace`) REFERENCES `Commande` (`idCommande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ComSurPlace`
--

LOCK TABLES `ComSurPlace` WRITE;
/*!40000 ALTER TABLE `ComSurPlace` DISABLE KEYS */;
INSERT INTO `ComSurPlace` VALUES (1,6,'midi'),(7,6,'midi'),(8,6,'midi'),(9,6,'soir'),(10,6,'soir'),(11,7,'soir'),(12,7,'soir'),(13,7,'soir'),(14,5,'midi'),(15,5,'midi'),(17,5,'midi'),(18,5,'midi'),(25,5,'midi');
/*!40000 ALTER TABLE `ComSurPlace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Commande`
--

DROP TABLE IF EXISTS `Commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Commande` (
  `idCommande` int NOT NULL,
  `dateCommande` date DEFAULT NULL,
  `heureCommande` date DEFAULT NULL,
  `prixCommande` int DEFAULT NULL,
  `statutCommande` varchar(30) DEFAULT NULL,
  `typeCommande` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `statutCommande` (`statutCommande`),
  KEY `typeCommande` (`typeCommande`),
  CONSTRAINT `Commande_ibfk_1` FOREIGN KEY (`statutCommande`) REFERENCES `Statut` (`statut`),
  CONSTRAINT `Commande_ibfk_2` FOREIGN KEY (`typeCommande`) REFERENCES `typeCommande` (`type`),
  CONSTRAINT `Commande_chk_1` CHECK ((`prixCommande` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Commande`
--

LOCK TABLES `Commande` WRITE;
/*!40000 ALTER TABLE `Commande` DISABLE KEYS */;
INSERT INTO `Commande` VALUES (1,'2022-11-13','2022-11-13',30,'attente de confirmation','surPlace'),(2,'2022-11-14','2022-11-14',30,'disponible','livraison'),(3,'2022-11-14','2022-11-14',30,'disponible','livraison'),(4,'2022-11-16','2022-11-16',30,'annulee par le client','emporte'),(5,'2022-11-17','2022-11-17',30,'attente de confirmation','emporte'),(7,'2022-11-13','2022-11-13',30,'validee','surPlace'),(8,'2022-11-13','2022-11-13',30,'validee','surPlace'),(9,'2022-11-13','2022-11-13',30,'validee','surPlace'),(10,'2022-11-13','2022-11-13',30,'validee','surPlace'),(11,'2022-11-13','2022-11-13',30,'validee','surPlace'),(12,'2022-11-14','2022-11-14',30,'validee','surPlace'),(13,'2022-11-14','2022-11-14',30,'validee','surPlace'),(14,'2022-12-04','2022-12-04',1,'attente de confirmation','livraison'),(15,'2022-12-04','2022-12-04',115,'validee','livraison'),(16,'2022-12-04','2022-12-04',1,'validee','emporte'),(17,'2022-12-04','2022-12-04',115,'validee','surPlace'),(18,'2022-12-04','2022-12-04',115,'validee','surPlace'),(19,'2022-12-04','2022-12-04',115,'validee','livraison'),(20,'2022-12-04','2022-12-04',115,'validee','livraison'),(21,'2022-12-04','2022-12-04',115,'validee','livraison'),(22,'2022-12-04','2022-12-04',115,'validee','livraison'),(23,'2022-12-04','2022-12-04',115,'validee','livraison'),(24,'2022-12-04','2022-12-04',115,'validee','emporte'),(25,'2022-12-04','2022-12-04',274,'validee','surPlace'),(26,'2022-12-04','2022-12-04',274,'disponible','emporte');
/*!40000 ALTER TABLE `Commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Evaluation`
--

DROP TABLE IF EXISTS `Evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Evaluation` (
  `idCommandeEval` int NOT NULL,
  `idRest` int DEFAULT NULL,
  `dateEval` date DEFAULT NULL,
  `heureEval` date DEFAULT NULL,
  `avisEval` varchar(100) DEFAULT NULL,
  `noteEval` int DEFAULT NULL,
  PRIMARY KEY (`idCommandeEval`),
  KEY `idRest` (`idRest`),
  CONSTRAINT `Evaluation_ibfk_1` FOREIGN KEY (`idRest`) REFERENCES `Restaurant` (`idRest`),
  CONSTRAINT `Evaluation_ibfk_2` FOREIGN KEY (`idCommandeEval`) REFERENCES `Commande` (`idCommande`),
  CONSTRAINT `Evaluation_chk_1` CHECK ((`noteEval` between 0 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Evaluation`
--

LOCK TABLES `Evaluation` WRITE;
/*!40000 ALTER TABLE `Evaluation` DISABLE KEYS */;
INSERT INTO `Evaluation` VALUES (1,1,'2022-11-13','2022-11-13','good food',4),(2,2,'2022-11-13','2022-11-13','good food',4),(3,3,'2022-11-13','2022-11-13','good food',4),(4,4,'2022-11-13','2022-11-13','good food',4),(5,5,'2022-11-13','2022-11-13','good food',4),(7,1,'2022-11-13','2022-11-13','good food',4),(8,6,'2022-11-13','2022-11-13','good food',4),(9,6,'2022-11-13','2022-11-13','good food',4),(10,4,'2022-11-13','2022-11-13','good food',3),(11,1,'2022-11-13','2022-11-13','good food',2),(12,1,'2022-11-14','2022-11-14','good food',5),(13,1,'2022-11-14','2022-11-14','bad food',2),(14,2,'2022-12-04','2022-12-04','good food',4),(15,2,'2022-12-04','2022-12-04','good food',4),(17,2,'2022-12-04','2022-12-04','good food',4),(18,2,'2022-12-04','2022-12-04','good food',4),(19,2,'2022-12-04','2022-12-04','good food',4),(20,2,'2022-12-04','2022-12-04','good food',4),(21,2,'2022-12-04','2022-12-04','good food',4),(24,2,'2022-12-04','2022-12-04','good food',4),(25,2,'2022-12-04','2022-12-04','good food',4),(26,2,'2022-12-04','2022-12-04','good food',5);
/*!40000 ALTER TABLE `Evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Horaire`
--

DROP TABLE IF EXISTS `Horaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Horaire` (
  `horaire` varchar(15) NOT NULL,
  PRIMARY KEY (`horaire`),
  CONSTRAINT `Horaire_chk_1` CHECK ((`horaire` in (_utf8mb4'midi',_utf8mb4'soir',_utf8mb4'midi et soir')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Horaire`
--

LOCK TABLES `Horaire` WRITE;
/*!40000 ALTER TABLE `Horaire` DISABLE KEYS */;
INSERT INTO `Horaire` VALUES ('midi'),('midi et soir'),('soir');
/*!40000 ALTER TABLE `Horaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Jour`
--

DROP TABLE IF EXISTS `Jour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Jour` (
  `jour` varchar(30) NOT NULL,
  PRIMARY KEY (`jour`),
  CONSTRAINT `Jour_chk_1` CHECK ((`jour` in (_utf8mb4'lundi',_utf8mb4'mardi',_utf8mb4'mercredi',_utf8mb4'jeudi',_utf8mb4'vendredi',_utf8mb4'samedi',_utf8mb4'dimanche')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Jour`
--

LOCK TABLES `Jour` WRITE;
/*!40000 ALTER TABLE `Jour` DISABLE KEYS */;
INSERT INTO `Jour` VALUES ('dimanche'),('jeudi'),('lundi'),('mardi'),('mercredi'),('samedi'),('vendredi');
/*!40000 ALTER TABLE `Jour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JourResto`
--

DROP TABLE IF EXISTS `JourResto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `JourResto` (
  `idRest` int NOT NULL,
  `jour` varchar(30) NOT NULL,
  PRIMARY KEY (`idRest`,`jour`),
  KEY `jour` (`jour`),
  CONSTRAINT `JourResto_ibfk_1` FOREIGN KEY (`jour`) REFERENCES `Jour` (`jour`),
  CONSTRAINT `JourResto_ibfk_2` FOREIGN KEY (`idRest`) REFERENCES `Restaurant` (`idRest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JourResto`
--

LOCK TABLES `JourResto` WRITE;
/*!40000 ALTER TABLE `JourResto` DISABLE KEYS */;
INSERT INTO `JourResto` VALUES (4,'dimanche'),(1,'jeudi'),(2,'jeudi'),(3,'jeudi'),(4,'jeudi'),(5,'jeudi'),(6,'jeudi'),(1,'lundi'),(2,'lundi'),(3,'lundi'),(4,'lundi'),(5,'lundi'),(6,'lundi'),(1,'mardi'),(2,'mardi'),(3,'mardi'),(4,'mardi'),(5,'mardi'),(6,'mardi'),(1,'mercredi'),(2,'mercredi'),(3,'mercredi'),(4,'mercredi'),(5,'mercredi'),(6,'mercredi'),(3,'samedi'),(6,'samedi'),(1,'vendredi'),(2,'vendredi'),(5,'vendredi');
/*!40000 ALTER TABLE `JourResto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `NbrPlaceRestante`
--

DROP TABLE IF EXISTS `NbrPlaceRestante`;
/*!50001 DROP VIEW IF EXISTS `NbrPlaceRestante`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `NbrPlaceRestante` AS SELECT 
 1 AS `idRest`,
 1 AS `nomRest`,
 1 AS `dateCommande`,
 1 AS `heureArriveSurPlace`,
 1 AS `placeRestante`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `NoteMoyenneDesRest`
--

DROP TABLE IF EXISTS `NoteMoyenneDesRest`;
/*!50001 DROP VIEW IF EXISTS `NoteMoyenneDesRest`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `NoteMoyenneDesRest` AS SELECT 
 1 AS `idRest`,
 1 AS `noteRest`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `PasserCommande`
--

DROP TABLE IF EXISTS `PasserCommande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PasserCommande` (
  `idClient` int NOT NULL,
  `idCommande` int NOT NULL,
  `idRest` int NOT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `idClient` (`idClient`),
  KEY `idRest` (`idRest`),
  CONSTRAINT `PasserCommande_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `Client` (`idClient`),
  CONSTRAINT `PasserCommande_ibfk_2` FOREIGN KEY (`idCommande`) REFERENCES `Commande` (`idCommande`),
  CONSTRAINT `PasserCommande_ibfk_3` FOREIGN KEY (`idRest`) REFERENCES `Restaurant` (`idRest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PasserCommande`
--

LOCK TABLES `PasserCommande` WRITE;
/*!40000 ALTER TABLE `PasserCommande` DISABLE KEYS */;
INSERT INTO `PasserCommande` VALUES (2,2,2),(3,3,3),(1,4,4),(4,5,5),(2,7,1),(1,8,6),(1,9,3),(3,10,4),(2,11,1),(2,12,1),(2,13,1),(3,15,1),(3,16,1),(3,17,1),(3,18,1),(3,19,1),(3,20,1),(3,21,1),(3,22,1),(3,23,1),(3,24,1),(3,25,2),(3,26,2);
/*!40000 ALTER TABLE `PasserCommande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Plat`
--

DROP TABLE IF EXISTS `Plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Plat` (
  `idPlat` int NOT NULL,
  `idRest` int NOT NULL,
  `nomPlat` varchar(30) DEFAULT NULL,
  `descPlat` varchar(100) DEFAULT NULL,
  `prixPlat` int DEFAULT NULL,
  PRIMARY KEY (`idPlat`,`idRest`),
  KEY `idRest` (`idRest`),
  CONSTRAINT `Plat_ibfk_1` FOREIGN KEY (`idRest`) REFERENCES `Restaurant` (`idRest`),
  CONSTRAINT `Plat_chk_1` CHECK ((`prixPlat` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Plat`
--

LOCK TABLES `Plat` WRITE;
/*!40000 ALTER TABLE `Plat` DISABLE KEYS */;
INSERT INTO `Plat` VALUES (1,1,'Menu naan kebab','Naan fromage, kebab, crudités et 2 sauces au choix. Servi avec frites et 1 boisson au choix.',10),(1,2,'2 MENUS + 2 EXTRAS','2 menus aux choix parmi une sélection de menus phares BURGER KING',23),(1,3,'5 Fromages','Base sauce tomate, mozzarella, chèvre, bleu, raclette et emmental.',15),(1,4,'Poke Sucré Saumon Wasabi','BRiz vinaigré Protéine : saumon mariné avec une sauce soja sucrée Légume : carotte cranberries ,',60),(1,5,'Nouilles Sautées aux légumes','Nouilles sautées aux légumes',20),(1,6,'Nougat glacé','Glace nougat façon Baba Louni',10),(2,1,'Maxi cheese naan kebab','Double viande, crudités et 2 sauces au choix.',9),(2,2,'3 MENUS + 3 EXTRAS','3 menus aux choix parmi une sélection de menus phares BURGER KING',30),(2,3,'Norvégienne','Base crème fraîche, mozzarella, saumon fumé, tomates cerises et citron.',16),(2,4,'Poke végétarien(vegan)','BDuo de céréales au curcuma \"quinoa et riz curcuma\" Protéine tofu grillé',70),(2,6,'Sandwich Babayon','Bouscoutou + glace Zabayon',11),(3,1,'classique kebab','Kebab, pain, crudités et 2 sauces au choix.',8),(3,2,'Tendercrisp Cheese & Bacon','LIrrésistible Tendercrisp et son poulet pané croustillant dans sa version cheese & bacon',11),(3,3,'Tartiflette','Base crème fraîche, mozzarella, pommes de terre, lardons, oignons et raclette.',14),(3,4,'Pastels boeuf x2','Beignets frits fourrés au boeuf. 2 pièces',55),(3,5,'M1','1 plat et 1 accompagnement au choix.',15),(3,6,'Bambaloni','Beignet Tunisien façon Sidi Bou Said',7),(4,1,'Mozzarella stick','apéritifs ou hors-dœuvre faits de fromage pané',15),(4,2,'Double Cheese Bacon XXL','Deux viandes de bœuf grillées à la flamme, des tranches de cheddar fondu et du bacon',12),(4,3,'3 Fondus','Base crème fraîche, mozzarella, lardons grillés',16),(4,4,'Alokos extra','Bananes plantains frites, légèrement croquantes à lextérieur',66),(4,5,'BO2 Bobun Au Bœuf','Grande salade, vermicelle, pousse de soja, concombre, carotte, bœuf, cacahuète',18),(4,6,'Boule au miel','La fameuse boule au miel tunisienne',6);
/*!40000 ALTER TABLE `Plat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlatsDeCommande`
--

DROP TABLE IF EXISTS `PlatsDeCommande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PlatsDeCommande` (
  `idCommande` int NOT NULL,
  `idRest` int NOT NULL,
  `idPlat` int NOT NULL,
  `Quantite` int DEFAULT NULL,
  PRIMARY KEY (`idCommande`,`idRest`,`idPlat`),
  KEY `idPlat` (`idPlat`,`idRest`),
  CONSTRAINT `PlatsDeCommande_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `Commande` (`idCommande`),
  CONSTRAINT `PlatsDeCommande_ibfk_2` FOREIGN KEY (`idPlat`, `idRest`) REFERENCES `Plat` (`idPlat`, `idRest`),
  CONSTRAINT `PlatsDeCommande_chk_1` CHECK ((`Quantite` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlatsDeCommande`
--

LOCK TABLES `PlatsDeCommande` WRITE;
/*!40000 ALTER TABLE `PlatsDeCommande` DISABLE KEYS */;
INSERT INTO `PlatsDeCommande` VALUES (1,1,1,3),(1,1,3,3),(2,2,1,3),(2,2,3,3),(3,3,1,3),(3,3,3,3),(4,4,2,3),(4,4,3,3),(5,5,3,3),(5,5,4,3),(7,1,1,3),(7,1,3,3),(8,6,2,3),(8,6,3,3),(9,3,1,3),(9,3,3,3),(10,4,2,1),(10,4,3,3),(11,1,2,1),(11,1,3,3),(12,4,2,1),(12,4,3,3),(13,1,2,1),(13,1,3,3),(14,1,1,3),(14,1,3,5),(15,1,1,3),(15,1,2,5),(15,1,3,5),(16,1,1,3),(16,1,2,5),(16,1,3,5),(17,1,1,3),(17,1,2,5),(17,1,3,5),(18,1,1,3),(18,1,2,5),(18,1,3,5),(19,1,1,3),(19,1,2,5),(19,1,3,5),(20,1,1,3),(20,1,2,5),(20,1,3,5),(21,1,1,3),(21,1,2,5),(21,1,3,5),(22,1,1,3),(22,1,2,5),(22,1,3,5),(23,1,1,3),(23,1,2,5),(23,1,3,5),(24,1,1,3),(24,1,2,5),(24,1,3,5),(25,2,1,3),(25,2,2,5),(25,2,3,5),(26,2,1,3),(26,2,2,5),(26,2,3,5);
/*!40000 ALTER TABLE `PlatsDeCommande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `PrixCommade`
--

DROP TABLE IF EXISTS `PrixCommade`;
/*!50001 DROP VIEW IF EXISTS `PrixCommade`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `PrixCommade` AS SELECT 
 1 AS `prixcommande`,
 1 AS `idCommande`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Restaurant`
--

DROP TABLE IF EXISTS `Restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Restaurant` (
  `idRest` int NOT NULL,
  `emailRest` varchar(30) NOT NULL,
  `nomRest` varchar(30) NOT NULL,
  `addrRest` varchar(100) NOT NULL,
  `nbPlaceRest` int DEFAULT NULL,
  `textPresentaionRest` varchar(100) DEFAULT NULL,
  `horaireOuvertureRest` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idRest`),
  UNIQUE KEY `emailRest` (`emailRest`),
  KEY `horaireOuvertureRest` (`horaireOuvertureRest`),
  CONSTRAINT `Restaurant_ibfk_1` FOREIGN KEY (`horaireOuvertureRest`) REFERENCES `Horaire` (`horaire`),
  CONSTRAINT `Restaurant_chk_1` CHECK ((`nbPlaceRest` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Restaurant`
--

LOCK TABLES `Restaurant` WRITE;
/*!40000 ALTER TABLE `Restaurant` DISABLE KEYS */;
INSERT INTO `Restaurant` VALUES (1,'bestkebab@gmail.com','OriginalKebab','2 rue de  Victor Hugo, Grenoble',20,'For the love of delicious kebab.','midi et soir'),(2,'FASTFOOD@gmail.com','FastFood','5 rue de Louis Pasteur, Grenoble',50,'Low cost. High quality.','midi et soir'),(3,'Pizza@gmail.com','World of Pizza','6 rue de Général Leclerc, Grenoble',10,'We are always here to serve you the best pizza in the world.','midi'),(4,'Blue_Diamond@gmail.com','Blue Diamond','2 rue boulevard, Grenoble',15,'High class restaurant for high class people','soir'),(5,'Dragonfly_China@gmail.com','Dragonfly China','17 rue de Stalingrad, Grenoble',30,'Every bowl of noodles is a surprise.','soir'),(6,'Tunisian_magic@gmail.com','Tunisian magic','17 rue de Victoire, Grenoble',50,'Best food that you will ever eat.','soir');
/*!40000 ALTER TABLE `Restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Statut`
--

DROP TABLE IF EXISTS `Statut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Statut` (
  `statut` varchar(100) NOT NULL,
  PRIMARY KEY (`statut`),
  CONSTRAINT `Statut_chk_1` CHECK ((`statut` in (_utf8mb4'attente de confirmation',_utf8mb4'validee',_utf8mb4'disponible',_utf8mb4'en livraison',_utf8mb4'annulee par le client',_utf8mb4'annulee par le restaurant')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Statut`
--

LOCK TABLES `Statut` WRITE;
/*!40000 ALTER TABLE `Statut` DISABLE KEYS */;
INSERT INTO `Statut` VALUES ('annulee par le client'),('annulee par le restaurant'),('attente de confirmation'),('disponible'),('en livraison'),('validee');
/*!40000 ALTER TABLE `Statut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeCommandeRest`
--

DROP TABLE IF EXISTS `TypeCommandeRest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TypeCommandeRest` (
  `idRest` int NOT NULL,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`idRest`,`type`),
  KEY `type` (`type`),
  CONSTRAINT `TypeCommandeRest_ibfk_1` FOREIGN KEY (`idRest`) REFERENCES `Restaurant` (`idRest`),
  CONSTRAINT `TypeCommandeRest_ibfk_2` FOREIGN KEY (`type`) REFERENCES `typeCommande` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeCommandeRest`
--

LOCK TABLES `TypeCommandeRest` WRITE;
/*!40000 ALTER TABLE `TypeCommandeRest` DISABLE KEYS */;
INSERT INTO `TypeCommandeRest` VALUES (1,'emporte'),(2,'emporte'),(3,'emporte'),(1,'livraison'),(2,'livraison'),(1,'surPlace'),(2,'surPlace'),(3,'surPlace'),(4,'surPlace'),(5,'surPlace'),(6,'surPlace');
/*!40000 ALTER TABLE `TypeCommandeRest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeCommande`
--

DROP TABLE IF EXISTS `typeCommande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeCommande` (
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`type`),
  CONSTRAINT `typeCommande_chk_1` CHECK ((`type` in (_utf8mb4'livraison',_utf8mb4'surPlace',_utf8mb4'emporte')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeCommande`
--

LOCK TABLES `typeCommande` WRITE;
/*!40000 ALTER TABLE `typeCommande` DISABLE KEYS */;
INSERT INTO `typeCommande` VALUES ('emporte'),('livraison'),('surPlace');
/*!40000 ALTER TABLE `typeCommande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `CategorieRestoAssocieOrdreDecroissant`
--

/*!50001 DROP VIEW IF EXISTS `CategorieRestoAssocieOrdreDecroissant`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `CategorieRestoAssocieOrdreDecroissant` AS select `CategorieRest`.`categorie` AS `categorie`,`Restaurant`.`idRest` AS `idRest`,`Restaurant`.`nomRest` AS `nomRest`,`NoteMoyenneDesRest`.`noteRest` AS `noteRest` from ((`CategorieRest` join `Restaurant` on((`Restaurant`.`idRest` = `CategorieRest`.`idRest`))) join `NoteMoyenneDesRest` on((`NoteMoyenneDesRest`.`idRest` = `Restaurant`.`idRest`))) group by `CategorieRest`.`categorie`,`CategorieRest`.`idRest` order by `NoteMoyenneDesRest`.`noteRest` desc,`Restaurant`.`nomRest` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `NbrPlaceRestante`
--

/*!50001 DROP VIEW IF EXISTS `NbrPlaceRestante`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `NbrPlaceRestante` AS select `Restaurant`.`idRest` AS `idRest`,`Restaurant`.`nomRest` AS `nomRest`,`Commande`.`dateCommande` AS `dateCommande`,`ComSurPlace`.`heureArriveSurPlace` AS `heureArriveSurPlace`,(`Restaurant`.`nbPlaceRest` - sum(`ComSurPlace`.`nbrPersonne`)) AS `placeRestante` from (((`PasserCommande` join `ComSurPlace` on((`ComSurPlace`.`idComSurPlace` = `PasserCommande`.`idCommande`))) join `Restaurant` on((`Restaurant`.`idRest` = `PasserCommande`.`idRest`))) join `Commande` on((`Commande`.`idCommande` = `PasserCommande`.`idCommande`))) where (`Commande`.`statutCommande` = 'validee') group by `Restaurant`.`idRest`,`Commande`.`dateCommande`,`ComSurPlace`.`heureArriveSurPlace`,`Restaurant`.`nomRest` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `NoteMoyenneDesRest`
--

/*!50001 DROP VIEW IF EXISTS `NoteMoyenneDesRest`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `NoteMoyenneDesRest` AS select `Evaluation`.`idRest` AS `idRest`,avg(`Evaluation`.`noteEval`) AS `noteRest` from `Evaluation` group by `Evaluation`.`idRest` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `PrixCommade`
--

/*!50001 DROP VIEW IF EXISTS `PrixCommade`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `PrixCommade` AS select sum((`Plat`.`prixPlat` * `PlatsDeCommande`.`Quantite`)) AS `prixcommande`,`PlatsDeCommande`.`idCommande` AS `idCommande` from (`Plat` join `PlatsDeCommande` on(((`Plat`.`idPlat` = `PlatsDeCommande`.`idPlat`) and (`Plat`.`idRest` = `PlatsDeCommande`.`idRest`)))) group by `PlatsDeCommande`.`idCommande` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-04 18:15:33
