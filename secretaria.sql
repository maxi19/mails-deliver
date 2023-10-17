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
-- Table structure for table `operacion`
--

DROP TABLE IF EXISTS `operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion` (
  `id` binary(16) NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `enviado` bit(1) NOT NULL,
  `matcheado` bit(1) NOT NULL,
  `recibo_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnhmgy4d8342x4rayx2s5213cw` (`recibo_id`),
  CONSTRAINT `FKnhmgy4d8342x4rayx2s5213cw` FOREIGN KEY (`recibo_id`) REFERENCES `recibo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `operacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` binary(16) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `patron` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (_binary '1\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ABRAHAM','noeliaabraham@fatimarem.edu.ar',' NOELIA SOLEDAD','ABRAHAM,NOELIASOLEDAD_.+.pdf'),(_binary '10\0\0\0\0\0\0\0\0\0\0\0\0\0\0','AMARELLE','nadiaamarelle@fatimarem.edu.ar',' NADIA MARIEL','AMARELLE,NADIAMARIEL_.+.pdf'),(_binary '100\0\0\0\0\0\0\0\0\0\0\0\0\0','YVER','marinayver@fatimarem.edu.ar',' MARINA ','YVER,MARINA_.+.pdf'),(_binary '101\0\0\0\0\0\0\0\0\0\0\0\0\0','ZELLA','mariozella@fatimarem.edu.ar',' MARIO ENRIQUE','ZELLA,MARIOENRIQUE_.+.pdf'),(_binary '11\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ANDREONI','norbertoandreoni@fatimarem.edu.ar',' NORBERTO JOS√â','ANDREONI,NORBERTOJOS√â_.+.pdf'),(_binary '12\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ARAG√ìN BARRAZA','lautaroaragon@fatimarem.edu.ar',' LAUTARO AGUST√çN','ARAG√ìNBARRAZA,LAUTAROAGUST√çN_.+.pdf'),(_binary '13\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ARECO','francoareco@fatimarem.edu.ar',' FRANCO DANIEL','ARECO,FRANCODANIEL_.+.pdf'),(_binary '14\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ARRIBAS','carlosarribas@fatimarem.edu.ar',' CARLOS ALBERTO','ARRIBAS,CARLOSALBERTO_.+.pdf'),(_binary '15\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BASSO','nataliabasso@fatimarem.edu.ar',' NATALIA SOLEDAD','BASSO,NATALIASOLEDAD_.+.pdf'),(_binary '16\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BEMBIBRE','ceciliabembibre@fatimarem.edu.ar',' CECILIA','BEMBIBRE,CECILIA_.+.pdf'),(_binary '17\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BENITEZ','gabrielabenitez@fatimarem.edu.ar',' GABRIELA YANINA','BENITEZ,GABRIELAYANINA_.+.pdf'),(_binary '18\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BERGALLI','alexiabergalli@fatimarem.edu.ar',' ALEXIA BEATRIZ','BERGALLI,ALEXIABEATRIZ_.+.pdf'),(_binary '19\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BERONE','maximilianoberone@fatimarem.edu.ar',' MAXIMILIANO EZEQUIEL','BERONE,MAXIMILIANOEZEQUIEL_.+.pdf'),(_binary '2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','AG√úERO','facundoaguero@fatimarem.edu.ar',' FACUNDO','AG√úERO,FACUNDO_.+.pdf'),(_binary '20\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BROESE','anabellabroese@fatimarem.edu.ar',' ANABELLA','BROESE,ANABELLA_.+.pdf'),(_binary '21\0\0\0\0\0\0\0\0\0\0\0\0\0\0','BOUNANOTTE','marielabuonanote@fatimarem.edu.ar',' VER√ìNICA MARIELA','BOUNANOTTE,VER√ìNICAMARIELA_.+.pdf'),(_binary '22\0\0\0\0\0\0\0\0\0\0\0\0\0\0','CAZZOLATO','fernandocazzolato@fatimarem.edu.ar',' FERNANDO','CAZZOLATO,FERNANDO_.+.pdf'),(_binary '23\0\0\0\0\0\0\0\0\0\0\0\0\0\0','CHAMORRO','blancachamorro@fatimarem.edu.ar',' BLANCA','CHAMORRO,BLANCA_.+.pdf'),(_binary '24\0\0\0\0\0\0\0\0\0\0\0\0\0\0','CHOQUE','lucianachoque@fatimarem.edu.ar',' LUCIANA EDITH','CHOQUE,LUCIANAEDITH_.+.pdf'),(_binary '25\0\0\0\0\0\0\0\0\0\0\0\0\0\0','CIPRIANO','marcelocipriano@fatimarem.edu.ar',' MARCELO JOS√â','CIPRIANO,MARCELOJOS√â_.+.pdf'),(_binary '26\0\0\0\0\0\0\0\0\0\0\0\0\0\0','COMAS','noracomas@fatimarem.edu.ar',' NORA ELISA','COMAS,NORAELISA_.+.pdf'),(_binary '27\0\0\0\0\0\0\0\0\0\0\0\0\0\0','COMAS','veronicacomas@fatimarem.edu.ar',' VER√ìNICA','COMAS,VER√ìNICA_.+.pdf'),(_binary '28\0\0\0\0\0\0\0\0\0\0\0\0\0\0','CUELLAR','lucianocuellar@fatimarem.edu.ar',' CECILIA MONICA','CUELLAR,CECILIAMONICA_.+.pdf'),(_binary '29\0\0\0\0\0\0\0\0\0\0\0\0\0\0','CUELLAR','ceciliacuellar@fatimarem.edu.ar',' LUCIANO SEBASTIAN','CUELLAR,LUCIANOSEBASTIAN_.+.pdf'),(_binary '3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','AG√úERO','tamaraaguero@fatimarem.edu.ar',' TAMARA ELIZABETH','AG√úERO,TAMARAELIZABETH_.+.pdf'),(_binary '30\0\0\0\0\0\0\0\0\0\0\0\0\0\0','DUARTE','claudioduarte@fatimarem.edu.ar',' CLAUDIO FERNANDO','DUARTE,CLAUDIOFERNANDO_.+.pdf'),(_binary '31\0\0\0\0\0\0\0\0\0\0\0\0\0\0','EITO','marianoeito@fatimarem.edu.ar',' MARIANO ANDR√âS','EITO,MARIANOANDR√âS_.+.pdf'),(_binary '32\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ESPEJO RIVERA','karenespejo@fatimarem.edu.ar',' KAREN JACKELINE','ESPEJORIVERA,KARENJACKELINE_.+.pdf'),(_binary '33\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ESPOSITO','marceloesposito@fatimarem.edu.ar',' MARCELO DANIEL','ESPOSITO,MARCELODANIEL_.+.pdf'),(_binary '34\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FAGINA','marcelofagina@fatimarem.edu.ar',' MARCELO RENE','FAGINA,MARCELORENE_.+.pdf'),(_binary '35\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FILGUEIRA','silvanafilgueira@fatimarem.edu.ar',' SILVANA','FILGUEIRA,SILVANA_.+.pdf'),(_binary '36\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FLORES','andreaflores@fatimarem.edu.ar',' MARIA ANDREA','FLORES,MARIAANDREA_.+.pdf'),(_binary '37\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FUENTES NAVIA','sandrafuentes@fatimarem.edu.ar',' SANDRA BEATRIZ','FUENTESNAVIA,SANDRABEATRIZ_.+.pdf'),(_binary '38\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FUNES','gabrielafunes@fatimarem.edu.ar',' MARIA GABRIELA','FUNES,MARIAGABRIELA_.+.pdf'),(_binary '39\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GARCIA','emilianogarcia@fatimarem.edu.ar',' PEREZ EMILIANO DAVID','GARCIA,PEREZEMILIANODAVID_.+.pdf'),(_binary '4\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','AGUILAR RODRIGUEZ','ailinaguilar@fatimarem.edu.ar',' AILIN','AGUILARRODRIGUEZ,AILIN_.+.pdf'),(_binary '40\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GIL','ernestogil@fatimarem.edu.ar',' ERNESTO JAVIER','GIL,ERNESTOJAVIER_.+.pdf'),(_binary '41\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GOMEZ','heidigomez@fatimarem.edu.ar',' HEIDI EDITH','GOMEZ,HEIDIEDITH_.+.pdf'),(_binary '42\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GONZALEZ','eduardogonzalez@fatimarem.edu.ar',' EDUARDO GABRIEL','GONZALEZ,EDUARDOGABRIEL_.+.pdf'),(_binary '43\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GONZALEZ','emilianogonzalez@fatimarem.edu.ar',' EMILIANO PABLO','GONZALEZ,EMILIANOPABLO_.+.pdf'),(_binary '44\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GONZALEZ','javiergonzales@fatimarem.edu.ar',' JAVIER CESAR','GONZALEZ,JAVIERCESAR_.+.pdf'),(_binary '45\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GONZALEZ','rosauragonzalez@fatimarem.edu.ar',' ROSAURA MONSERRAT','GONZALEZ,ROSAURAMONSERRAT_.+.pdf'),(_binary '46\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GRANDE','nicolasgrande@fatimarem.edu.ar',' NICOLAS ','GRANDE,NICOLAS_.+.pdf'),(_binary '47\0\0\0\0\0\0\0\0\0\0\0\0\0\0','GUZMAN CUNO','maximilianoguzman@fatimarem.edu.ar',' JOSE MAXIMILIANO','GUZMANCUNO,JOSEMAXIMILIANO_.+.pdf'),(_binary '48\0\0\0\0\0\0\0\0\0\0\0\0\0\0','HADJINIAN','silviahadjinian@fatimarem.edu.ar',' SILVIA BEATRIZ','HADJINIAN,SILVIABEATRIZ_.+.pdf'),(_binary '49\0\0\0\0\0\0\0\0\0\0\0\0\0\0','HERLEIN','juanpabloherlein@fatimarem.edu.ar',' JUAN PABLO','HERLEIN,JUANPABLO_.+.pdf'),(_binary '5\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','AGUILAR RODRIGUEZ','leslieaguilar@fatimarem.edu.ar',' LESLIE','AGUILARRODRIGUEZ,LESLIE_.+.pdf'),(_binary '50\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LAFORGIA','marcelalaforgia@fatimarem.edu.ar',' MARCELA FLAVIA','LAFORGIA,MARCELAFLAVIA_.+.pdf'),(_binary '51\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LAMANNA','lauralamanna@fatimarem.edu.ar',' MARIA LAURA','LAMANNA,MARIALAURA_.+.pdf'),(_binary '52\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LESCANO','andreslescano@fatimarem.edu.ar',' ANDR√âS NAZARENO','LESCANO,ANDR√âSNAZARENO_.+.pdf'),(_binary '53\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LEYBOVICH','marceloleybovich@fatimarem.edu.ar','MARCELO','LEYBOVICH,MARCELO_.+.pdf'),(_binary '54\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LEYBOVICH','pabloleybovich@fatimarem.edu.ar',' PABLO','LEYBOVICH,PABLO_.+.pdf'),(_binary '55\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LODEIRO','lucianalodeiro@fatimarem.edu.ar',' LUCIANA','LODEIRO,LUCIANA_.+.pdf'),(_binary '56\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LOPEZ','leonardolopez@fatimarem.edu.ar',' LEONARDO','LOPEZ,LEONARDO_.+.pdf'),(_binary '57\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LOSICER','jonathanlosicer@fatimarem.edu.ar',' JONATHAN GABRIEL','LOSICER,JONATHANGABRIEL_.+.pdf'),(_binary '58\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LUQUEZ','alicialuquez@fatimarem.edu.ar',' ALICIA ELIZABETH','LUQUEZ,ALICIAELIZABETH_.+.pdf'),(_binary '59\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MACIEL','patriciamaciel@fatimarem.edu.ar',' SANDRA PATRICIA','MACIEL,SANDRAPATRICIA_.+.pdf'),(_binary '6\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','AGUILAR RODRIGUEZ','sergioaguilar@fatimarem.edu.ar',' SERGIO','AGUILARRODRIGUEZ,SERGIO_.+.pdf'),(_binary '60\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MACRI','valeriamacri@fatimarem.edu.ar',' VALERIA HILDA','MACRI,VALERIAHILDA_.+.pdf'),(_binary '61\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MANCINI','alanmancini@fatimarem.edu.ar',' ALAN','MANCINI,ALAN_.+.pdf'),(_binary '62\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MARTINEZ','carlosmartinez@fatimarem.edu.ar',' CARLOS RAMON','MARTINEZ,CARLOSRAMON_.+.pdf'),(_binary '63\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MARTINEZ','elviomartinez@fatimarem.edu.ar',' ELVIO MARCELO','MARTINEZ,ELVIOMARCELO_.+.pdf'),(_binary '64\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MASCARE√ëO','rosaliamascareno@fatimarem.edu.ar',' ROSALIA','MASCARE√ëO,ROSALIA_.+.pdf'),(_binary '65\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MENDEZ','patriciomendez@fatimarem.edu.ar',' PATRICIO','MENDEZ,PATRICIO_.+.pdf'),(_binary '66\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MOLL','samantamoll@fatimarem.edu.ar',' SAMANTA JULIETA','MOLL,SAMANTAJULIETA_.+.pdf'),(_binary '67\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MORALES','gabrielamorales@fatimarem.edu.ar',' GABRIELA','MORALES,GABRIELA_.+.pdf'),(_binary '68\0\0\0\0\0\0\0\0\0\0\0\0\0\0','MU√ëOZ','sebastianmunoz@fatimarem.edu.ar',' PEDRO SEBASTIAN','MU√ëOZ,PEDROSEBASTIAN_.+.pdf'),(_binary '69\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ORREGO OJEDA','federicoojeda@fatimarem.edu.ar',' FEDERICO','ORREGOOJEDA,FEDERICO_.+.pdf'),(_binary '7\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ALATO','mariasolalato@fatimarem.edu.ar',' MARIA SOL','ALATO,MARIASOL_.+.pdf'),(_binary '70\0\0\0\0\0\0\0\0\0\0\0\0\0\0','PATI√ëO MNDOZA','flaviopatino@fatimarem.edu.ar',' FLAVIO','PATI√ëOMNDOZA,FLAVIO_.+.pdf'),(_binary '71\0\0\0\0\0\0\0\0\0\0\0\0\0\0','PETRUK','fernandopetruk@fatimarem.edu.ar',' FERNANDO SEBASTIAN','PETRUK,FERNANDOSEBASTIAN_.+.pdf'),(_binary '72\0\0\0\0\0\0\0\0\0\0\0\0\0\0','QUICHO','anaquicho@fatimarem.edu.ar',' ANA MARIA','QUICHO,ANAMARIA_.+.pdf'),(_binary '73\0\0\0\0\0\0\0\0\0\0\0\0\0\0','RADOVIC','marceloradovic@fatimarem.edu.ar',' LEONARDO MARCELO','RADOVIC,LEONARDOMARCELO_.+.pdf'),(_binary '74\0\0\0\0\0\0\0\0\0\0\0\0\0\0','RAMIREZ BERNAL','elianaramirez@fatimarem.edu.ar',' ROSA ELIANA','RAMIREZBERNAL,ROSAELIANA_.+.pdf'),(_binary '75\0\0\0\0\0\0\0\0\0\0\0\0\0\0','REINOSO','karinareinoso@fatimarem.edu.ar',' KARINA NANCI','REINOSO,KARINANANCI_.+.pdf'),(_binary '76\0\0\0\0\0\0\0\0\0\0\0\0\0\0','RIVAS ARIAS','danyrivas@fatimarem.edu.ar',' DANY JOSE','RIVASARIAS,DANYJOSE_.+.pdf'),(_binary '77\0\0\0\0\0\0\0\0\0\0\0\0\0\0','RODRIGUEZ','martarodriguez@fatimarem.edu.ar',' MARTA ISABEL','RODRIGUEZ,MARTAISABEL_.+.pdf'),(_binary '78\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ROMERO','luisromero@fatimarem.edu.ar',' LUIS GABRIEL','ROMERO,LUISGABRIEL_.+.pdf'),(_binary '79\0\0\0\0\0\0\0\0\0\0\0\0\0\0','RUIBAL','martinruibal@fatimarem.edu.ar',' MARTIN GABRIEL','RUIBAL,MARTINGABRIEL_.+.pdf'),(_binary '8\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ALMIRON','juanalmiron@fatimarem.edu.ar',' JUAN CARLOS','ALMIRON,JUANCARLOS_.+.pdf'),(_binary '80\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SALINAS','gracielasalinas@fatimarem.edu.ar',' GRACIELA BEATRIZ','SALINAS,GRACIELABEATRIZ_.+.pdf'),(_binary '81\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SANDOVAL','javiersandoval@fatimarem.edu.ar',' JAVIER ALEJANDRO','SANDOVAL,JAVIERALEJANDRO_.+.pdf'),(_binary '82\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SANTOS AIZA','anasantos@fatimarem.edu.ar',' ANA MARIA','SANTOSAIZA,ANAMARIA_.+.pdf'),(_binary '83\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SAVEL√çN SALUZI','nicolassavelon@fatimarem.edu.ar',' NICOLAS','SAVEL√çNSALUZI,NICOLAS_.+.pdf'),(_binary '84\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SAYANES SORIA','elizabethsayanes@fatimarem.edu.ar',' ELIZABETH','SAYANESSORIA,ELIZABETH_.+.pdf'),(_binary '85\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SEGATO','tomassegato@fatimarem.edu.ar',' TOMAS ','SEGATO,TOMAS_.+.pdf'),(_binary '86\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SEP√öLVEDA FERN√ÅNDEZ','carolinasepulveda@fatimarem.edu.ar',' CAROLINA','SEP√öLVEDAFERN√ÅNDEZ,CAROLINA_.+.pdf'),(_binary '87\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SIERRA','federicosierra@fatimarem.edu.ar',' FEDERICO','SIERRA,FEDERICO_.+.pdf'),(_binary '88\0\0\0\0\0\0\0\0\0\0\0\0\0\0','STANCIC','andreastancic@fatimarem.edu.ar',' ANDREA JUDITH','STANCIC,ANDREAJUDITH_.+.pdf'),(_binary '89\0\0\0\0\0\0\0\0\0\0\0\0\0\0','SZYSZKA','agustinszyszca@fatimarem.edu.ar',' GABRIEL AGUST√çN','SZYSZKA,GABRIELAGUST√çN_.+.pdf'),(_binary '9\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ALONSO','nazarenaalonso@fatimarem.edu.ar',' NAZARENA NOEMI','ALONSO,NAZARENANOEMI_.+.pdf'),(_binary '90\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TABORDA AYALA','sofiataborda@fatimarem.edu.ar',' SOF√çA EUGENIA','TABORDAAYALA,SOF√çAEUGENIA_.+.pdf'),(_binary '91\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TASSONE','guillermotassone@fatimarem.edu.ar',' GUILLERMO OSVALDO','TASSONE,GUILLERMOOSVALDO_.+.pdf'),(_binary '92\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TATAY ETCHEVERRY','tataymaite@fatimarem.edu.ar',' MAITE STELLA MARIS','TATAYETCHEVERRY,MAITESTELLAMARIS_.+.pdf'),(_binary '93\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TERRAZAS BUEZO','mariajoseterrazas@fatimarem.edu.ar',' MAR√çA JOS√â','TERRAZASBUEZO,MAR√çAJOS√â_.+.pdf'),(_binary '94\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TESEI','flaviotesei@fatimarem.edu.ar',' FLAVIO','TESEI,FLAVIO_.+.pdf'),(_binary '95\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TOCALINI','andrestocalini@fatimarem.edu.ar',' ANDRES','TOCALINI,ANDRES_.+.pdf'),(_binary '96\0\0\0\0\0\0\0\0\0\0\0\0\0\0','TORRE','teresatorre@fatimarem.edu.ar',' TERESA ELIZABETH','TORRE,TERESAELIZABETH_.+.pdf'),(_binary '97\0\0\0\0\0\0\0\0\0\0\0\0\0\0','UREY PEREZ','emilceurey@fatimarem.edu.ar',' EMILCE','UREYPEREZ,EMILCE_.+.pdf'),(_binary '98\0\0\0\0\0\0\0\0\0\0\0\0\0\0','VERA','andreavera@fatimarem.edu.ar',' ANDREA SOLEDAD','VERA,ANDREASOLEDAD_.+.pdf'),(_binary '99\0\0\0\0\0\0\0\0\0\0\0\0\0\0','VIDALES','silvinavidales@fatimarem.edu.ar',' SILVINA LAURA','VIDALES,SILVINALAURA_.+.pdf'),(_binary 'c\Ê(\Zè[E^É\ÍgöKƒ¢','blanco davalos','aleajandroblanco318@gmail.com','alejandro facundo','BLANCODAVALOS,ALEJANDROFACUNDO_.+.pdf');
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `id` binary(16) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `personal_id` binary(16) NOT NULL,
  `enviado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK289iucrxhbfah91whq133r5k9` (`personal_id`),
  CONSTRAINT `FK289iucrxhbfah91whq133r5k9` FOREIGN KEY (`personal_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
INSERT INTO `recibo` VALUES (_binary 'ä¬öpyCaø`\ ‘Éj≥!','BLANCO DAVALOS, ALEJANDRO FACUNDO_aslkdhaslfhaf.pdf',_binary 'c\Ê(\Zè[E^É\ÍgöKƒ¢',NULL),(_binary 'Ç‘ü	\€˝H,æ∏É\–|\√\≈\≈','BLANCO DAVALOS, ALEJANDRO FACUNDO_askldjaskd.pdf',_binary 'c\Ê(\Zè[E^É\ÍgöKƒ¢',NULL),(_binary 'Ëí¢πK4M`ö\ËT\·ﬂ∏≠','BLANCO DAVALOS, ALEJANDRO FACUNDO_aslkdhaslfhaf.pdf',_binary 'c\Ê(\Zè[E^É\ÍgöKƒ¢',NULL),(_binary '˚ôªª∞MÑí{\“D2]\0','BLANCO DAVALOS, ALEJANDRO FACUNDO_askldjaskd.pdf',_binary 'c\Ê(\Zè[E^É\ÍgöKƒ¢',NULL);
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo_sin_identificar`
--

DROP TABLE IF EXISTS `recibo_sin_identificar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo_sin_identificar` (
  `id` binary(16) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo_sin_identificar`
--

LOCK TABLES `recibo_sin_identificar` WRITE;
/*!40000 ALTER TABLE `recibo_sin_identificar` DISABLE KEYS */;
INSERT INTO `recibo_sin_identificar` VALUES (_binary '`[óäFºáê≤s\Ã¯Aí','BLANCO DAVALOS, ALEJANDRO FACUNDO_aslkdhaslfhaf.pdf'),(_binary '\Ó\¬XërLﬂøÑ\À&vÉ	','BLANCO DAVALOS, ALEJANDRO FACUNDO_askldjaskd.pdf');
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

-- Dump completed on 2023-10-16 23:53:24
