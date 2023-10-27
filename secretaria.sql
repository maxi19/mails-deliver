-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: secretaria
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `patron` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'HUALLPA VILLA','abii555villegas@gmail.com','YOEL RONALD',''),(2,'ABRAHAM','noeliaabraham@fatimarem.edu.ar',' NOELIA SOLEDAD',''),(3,'AMARELLE','nadiaamarelle@fatimarem.edu.ar',' NADIA MARIEL',''),(4,'YVER','marinayver@fatimarem.edu.ar',' MARINA ',''),(5,'ZELLA','mariozella@fatimarem.edu.ar',' MARIO ENRIQUE',''),(6,'ANDREONI','norbertoandreoni@fatimarem.edu.ar',' NORBERTO JOSÉ',''),(7,'ARAGÓN BARRAZA','lautaroaragon@fatimarem.edu.ar',' LAUTARO AGUSTÍN',''),(8,'ARECO','francoareco@fatimarem.edu.ar',' FRANCO DANIEL',''),(9,'ARRIBAS','carlosarribas@fatimarem.edu.ar',' CARLOS ALBERTO',''),(10,'BASSO','nataliabasso@fatimarem.edu.ar',' NATALIA SOLEDAD',''),(11,'BEMBIBRE','ceciliabembibre@fatimarem.edu.ar',' CECILIA',''),(12,'BENITEZ','gabrielabenitez@fatimarem.edu.ar',' GABRIELA YANINA',''),(13,'BERGALLI','alexiabergalli@fatimarem.edu.ar',' ALEXIA BEATRIZ',''),(14,'BERONE','maximilianoberone@fatimarem.edu.ar',' MAXIMILIANO EZEQUIEL',''),(15,'AGÜERO','facundoaguero@fatimarem.edu.ar',' FACUNDO',''),(16,'BROESE','anabellabroese@fatimarem.edu.ar',' ANABELLA',''),(17,'BOUNANOTTE','marielabuonanote@fatimarem.edu.ar',' VERÓNICA MARIELA',''),(18,'CAZZOLATO','fernandocazzolato@fatimarem.edu.ar',' FERNANDO',''),(19,'CHAMORRO','blancachamorro@fatimarem.edu.ar',' BLANCA',''),(20,'CHOQUE','lucianachoque@fatimarem.edu.ar',' LUCIANA EDITH',''),(21,'CIPRIANO','marcelocipriano@fatimarem.edu.ar',' MARCELO JOSÉ',''),(22,'COMAS','noracomas@fatimarem.edu.ar',' NORA ELISA',''),(23,'COMAS','veronicacomas@fatimarem.edu.ar',' VERÓNICA',''),(24,'CUELLAR','lucianocuellar@fatimarem.edu.ar',' CECILIA MONICA',''),(25,'CUELLAR','ceciliacuellar@fatimarem.edu.ar',' LUCIANO SEBASTIAN',''),(26,'AGÜERO','tamaraaguero@fatimarem.edu.ar',' TAMARA ELIZABETH',''),(27,'DUARTE','claudioduarte@fatimarem.edu.ar',' CLAUDIO FERNANDO',''),(28,'EITO','marianoeito@fatimarem.edu.ar',' MARIANO ANDRÉS',''),(29,'ESPEJO RIVERA','karenespejo@fatimarem.edu.ar',' KAREN JACKELINE',''),(30,'ESPOSITO','marceloesposito@fatimarem.edu.ar',' MARCELO DANIEL',''),(31,'FAGINA','marcelofagina@fatimarem.edu.ar',' MARCELO RENE',''),(32,'FILGUEIRA','silvanafilgueira@fatimarem.edu.ar',' SILVANA',''),(33,'FLORES','andreaflores@fatimarem.edu.ar',' MARIA ANDREA',''),(34,'FUENTES NAVIA','sandrafuentes@fatimarem.edu.ar',' SANDRA BEATRIZ',''),(35,'FUNES','gabrielafunes@fatimarem.edu.ar',' MARIA GABRIELA',''),(36,'GARCIA','emilianogarcia@fatimarem.edu.ar',' PEREZ EMILIANO DAVID',''),(37,'AGUILAR RODRIGUEZ','ailinaguilar@fatimarem.edu.ar',' AILIN',''),(38,'GIL','ernestogil@fatimarem.edu.ar',' ERNESTO JAVIER',''),(39,'GOMEZ','heidigomez@fatimarem.edu.ar',' HEIDI EDITH',''),(40,'GONZALEZ','eduardogonzalez@fatimarem.edu.ar',' EDUARDO GABRIEL',''),(41,'GONZALEZ','emilianogonzalez@fatimarem.edu.ar',' EMILIANO PABLO',''),(42,'GONZALEZ','javiergonzales@fatimarem.edu.ar',' JAVIER CESAR',''),(43,'GONZALEZ','rosauragonzalez@fatimarem.edu.ar',' ROSAURA MONSERRAT',''),(44,'GRANDE','nicolasgrande@fatimarem.edu.ar',' NICOLAS ',''),(45,'GUZMAN CUNO','maximilianoguzman@fatimarem.edu.ar',' JOSE MAXIMILIANO',''),(46,'HADJINIAN','silviahadjinian@fatimarem.edu.ar',' SILVIA BEATRIZ',''),(47,'HERLEIN','juanpabloherlein@fatimarem.edu.ar',' JUAN PABLO',''),(48,'AGUILAR RODRIGUEZ','leslieaguilar@fatimarem.edu.ar',' LESLIE',''),(49,'LAFORGIA','marcelalaforgia@fatimarem.edu.ar',' MARCELA FLAVIA',''),(50,'LAMANNA','lauralamanna@fatimarem.edu.ar',' MARIA LAURA',''),(51,'LESCANO','andreslescano@fatimarem.edu.ar',' ANDRÉS NAZARENO',''),(52,'LEYBOVICH','marceloleybovich@fatimarem.edu.ar','MARCELO',''),(53,'LEYBOVICH','pabloleybovich@fatimarem.edu.ar',' PABLO',''),(54,'LODEIRO','lucianalodeiro@fatimarem.edu.ar',' LUCIANA',''),(55,'LOPEZ','leonardolopez@fatimarem.edu.ar',' LEONARDO',''),(56,'LOSICER','jonathanlosicer@fatimarem.edu.ar',' JONATHAN GABRIEL',''),(57,'LUQUEZ','alicialuquez@fatimarem.edu.ar',' ALICIA ELIZABETH',''),(58,'MACIEL','patriciamaciel@fatimarem.edu.ar',' SANDRA PATRICIA',''),(59,'AGUILAR RODRIGUEZ','sergioaguilar@fatimarem.edu.ar',' SERGIO',''),(60,'MACRI','valeriamacri@fatimarem.edu.ar',' VALERIA HILDA',''),(61,'MANCINI','alanmancini@fatimarem.edu.ar',' ALAN',''),(62,'MARTINEZ','carlosmartinez@fatimarem.edu.ar',' CARLOS RAMON',''),(63,'MARTINEZ','elviomartinez@fatimarem.edu.ar',' ELVIO MARCELO',''),(64,'MASCAREÑO','rosaliamascareno@fatimarem.edu.ar',' ROSALIA',''),(65,'MENDEZ','patriciomendez@fatimarem.edu.ar',' PATRICIO',''),(66,'MOLL','samantamoll@fatimarem.edu.ar',' SAMANTA JULIETA',''),(67,'MORALES','gabrielamorales@fatimarem.edu.ar',' GABRIELA',''),(68,'MUÑOZ','sebastianmunoz@fatimarem.edu.ar',' PEDRO SEBASTIAN',''),(69,'ORREGO OJEDA','federicoojeda@fatimarem.edu.ar',' FEDERICO',''),(70,'ALATO','mariasolalato@fatimarem.edu.ar',' MARIA SOL',''),(71,'PATIÑO MNDOZA','flaviopatino@fatimarem.edu.ar',' FLAVIO',''),(72,'PETRUK','fernandopetruk@fatimarem.edu.ar',' FERNANDO SEBASTIAN',''),(73,'QUICHO','anaquicho@fatimarem.edu.ar',' ANA MARIA',''),(74,'RADOVIC','marceloradovic@fatimarem.edu.ar',' LEONARDO MARCELO',''),(75,'RAMIREZ BERNAL','elianaramirez@fatimarem.edu.ar',' ROSA ELIANA',''),(76,'REINOSO','karinareinoso@fatimarem.edu.ar',' KARINA NANCI',''),(77,'RIVAS ARIAS','danyrivas@fatimarem.edu.ar',' DANY JOSE',''),(78,'RODRIGUEZ','martarodriguez@fatimarem.edu.ar',' MARTA ISABEL',''),(79,'ROMERO','luisromero@fatimarem.edu.ar',' LUIS GABRIEL',''),(80,'RUIBAL','martinruibal@fatimarem.edu.ar',' MARTIN GABRIEL',''),(81,'ALMIRON','juanalmiron@fatimarem.edu.ar',' JUAN CARLOS',''),(82,'SALINAS','gracielasalinas@fatimarem.edu.ar',' GRACIELA BEATRIZ',''),(83,'SANDOVAL','javiersandoval@fatimarem.edu.ar',' JAVIER ALEJANDRO',''),(84,'SANTOS AIZA','anasantos@fatimarem.edu.ar',' ANA MARIA',''),(85,'SAVELÍN SALUZI','nicolassavelon@fatimarem.edu.ar',' NICOLAS',''),(86,'SAYANES SORIA','elizabethsayanes@fatimarem.edu.ar',' ELIZABETH',''),(87,'SEGATO','tomassegato@fatimarem.edu.ar',' TOMAS ',''),(88,'SEPÚLVEDA FERNÁNDEZ','carolinasepulveda@fatimarem.edu.ar',' CAROLINA',''),(89,'SIERRA','federicosierra@fatimarem.edu.ar',' FEDERICO',''),(90,'STANCIC','andreastancic@fatimarem.edu.ar',' ANDREA JUDITH',''),(91,'SZYSZKA','agustinszyszca@fatimarem.edu.ar',' GABRIEL AGUSTÍN',''),(92,'ALONSO','nazarenaalonso@fatimarem.edu.ar',' NAZARENA NOEMI',''),(93,'TABORDA AYALA','sofiataborda@fatimarem.edu.ar',' SOFÍA EUGENIA',''),(94,'TASSONE','guillermotassone@fatimarem.edu.ar',' GUILLERMO OSVALDO',''),(95,'TATAY ETCHEVERRY','tataymaite@fatimarem.edu.ar',' MAITE STELLA MARIS',''),(96,'TERRAZAS BUEZO','mariajoseterrazas@fatimarem.edu.ar',' MARÍA JOSÉ',''),(97,'TESEI','flaviotesei@fatimarem.edu.ar',' FLAVIO',''),(98,'TOCALINI','andrestocalini@fatimarem.edu.ar',' ANDRES',''),(99,'TORRE','teresatorre@fatimarem.edu.ar',' TERESA ELIZABETH',''),(100,'UREY PEREZ','emilceurey@fatimarem.edu.ar',' EMILCE',''),(101,'VERA','andreavera@fatimarem.edu.ar',' ANDREA SOLEDAD',''),(102,'VIDALES','silvinavidales@fatimarem.edu.ar',' SILVINA LAURA','');
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo_enviado`
--

DROP TABLE IF EXISTS `recibo_enviado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo_enviado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `personal_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6fth0jy50i7krf1l082eh9105` (`personal_id`),
  CONSTRAINT `FK6fth0jy50i7krf1l082eh9105` FOREIGN KEY (`personal_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo_enviado`
--

LOCK TABLES `recibo_enviado` WRITE;
/*!40000 ALTER TABLE `recibo_enviado` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo_enviado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo_identificado`
--

DROP TABLE IF EXISTS `recibo_identificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo_identificado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recibo` varchar(255) DEFAULT NULL,
  `personal` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm4m068un3v6c03rxnb2ofusn3` (`personal`),
  CONSTRAINT `FKm4m068un3v6c03rxnb2ofusn3` FOREIGN KEY (`personal`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo_identificado`
--

LOCK TABLES `recibo_identificado` WRITE;
/*!40000 ALTER TABLE `recibo_identificado` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo_identificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo_sin_identificar`
--

DROP TABLE IF EXISTS `recibo_sin_identificar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo_sin_identificar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=841 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo_sin_identificar`
--

LOCK TABLES `recibo_sin_identificar` WRITE;
/*!40000 ALTER TABLE `recibo_sin_identificar` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo_sin_identificar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-27 14:27:38
