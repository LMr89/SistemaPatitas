-- MariaDB dump 10.19  Distrib 10.8.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: BDPatitas
-- ------------------------------------------------------
-- Server version	10.8.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Accesos`
--

DROP TABLE IF EXISTS `Accesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Accesos` (
  `idAccesos` int(11) NOT NULL AUTO_INCREMENT,
  `GestionCitas` tinyint(1) NOT NULL,
  `GestionAtencionV` tinyint(1) NOT NULL,
  `GestionAdmision` tinyint(1) NOT NULL,
  `GestionSeguridad` tinyint(1) NOT NULL,
  PRIMARY KEY (`idAccesos`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Accesos`
--

LOCK TABLES `Accesos` WRITE;
/*!40000 ALTER TABLE `Accesos` DISABLE KEYS */;
INSERT INTO `Accesos` VALUES
(1,1,1,1,1),
(2,1,0,1,0),
(3,0,1,0,0);
/*!40000 ALTER TABLE `Accesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Administrador`
--

DROP TABLE IF EXISTS `Administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Administrador` (
  `idAdministrador` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  KEY `idUsuario_idx` (`idUsuario`),
  CONSTRAINT `idUsuarioA` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
INSERT INTO `Administrador` VALUES
(1,1);
/*!40000 ALTER TABLE `Administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cita`
--

DROP TABLE IF EXISTS `Cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cita` (
  `idCita` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `idMascota` int(11) NOT NULL,
  `idVeterinario` int(11) NOT NULL,
  `idRecepcionista` int(11) NOT NULL,
  `FechaRegistro` datetime NOT NULL,
  `FechaAtencion` datetime NOT NULL,
  `Pendiente` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCita`),
  KEY `idCliente_idx` (`idCliente`),
  KEY `idMascota_idx` (`idMascota`),
  KEY `idVeterinario_idx` (`idVeterinario`),
  KEY `idRecepcionista_idx` (`idRecepcionista`),
  CONSTRAINT `idClienteC` FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idMascotaC` FOREIGN KEY (`idMascota`) REFERENCES `Mascota` (`idMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idRecepcionistaC` FOREIGN KEY (`idRecepcionista`) REFERENCES `Recepcionista` (`idRecepcionista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idVeterinarioC` FOREIGN KEY (`idVeterinario`) REFERENCES `Veterinario` (`idVeterinario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cita`
--

LOCK TABLES `Cita` WRITE;
/*!40000 ALTER TABLE `Cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `DNI` char(8) NOT NULL,
  `Direccion` varchar(60) NOT NULL,
  `Correo` varchar(50) NOT NULL,
  `Telefono` char(9) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `DNI_UNIQUE` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES
(1,'Ciro ','Tiamana','85632147','Los mangos 145','cta@gmail.com','985632147',1),
(2,'Henri','Zarate','23659874','Emiliano 48 JLO','sonic@gmail.com','965321489',1),
(3,'Xiomara','Vasquez','56329874','Manglares 89 SM','idat89@gmail.com','968569853',1),
(4,'Erick','Huaman','85632975','Quiñonez 56','erc98@gmail.com','963636359',1);
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Diagnostico`
--

DROP TABLE IF EXISTS `Diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Diagnostico` (
  `idDiagnostico` int(11) NOT NULL AUTO_INCREMENT,
  `idVeterinario` int(11) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Detalles` varchar(100) NOT NULL,
  `idHistoriaClinica` int(11) NOT NULL,
  PRIMARY KEY (`idDiagnostico`),
  KEY `idVeterinario_idx` (`idVeterinario`),
  KEY `fk_idHistoria` (`idHistoriaClinica`),
  CONSTRAINT `fk_idHistoria` FOREIGN KEY (`idHistoriaClinica`) REFERENCES `HistoriaClinica` (`idHistoriaClinica`),
  CONSTRAINT `idVeterinario` FOREIGN KEY (`idVeterinario`) REFERENCES `Veterinario` (`idVeterinario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Diagnostico`
--

LOCK TABLES `Diagnostico` WRITE;
/*!40000 ALTER TABLE `Diagnostico` DISABLE KEYS */;
INSERT INTO `Diagnostico` VALUES
(1,1,'2022-06-04 00:00:00','Breve alergia debido a las garrapatas',1),
(2,1,'2022-06-04 00:00:00','Patita quebrada',2);
/*!40000 ALTER TABLE `Diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HistoriaClinica`
--

DROP TABLE IF EXISTS `HistoriaClinica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HistoriaClinica` (
  `idHistoriaClinica` int(11) NOT NULL AUTO_INCREMENT,
  `idMascota` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idHistoriaClinica`),
  KEY `idMascota_idx` (`idMascota`),
  CONSTRAINT `idMascota` FOREIGN KEY (`idMascota`) REFERENCES `Mascota` (`idMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HistoriaClinica`
--

LOCK TABLES `HistoriaClinica` WRITE;
/*!40000 ALTER TABLE `HistoriaClinica` DISABLE KEYS */;
INSERT INTO `HistoriaClinica` VALUES
(1,1,1),
(2,2,1),
(3,3,1),
(4,4,1),
(5,5,1),
(6,6,1);
/*!40000 ALTER TABLE `HistoriaClinica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mascota`
--

DROP TABLE IF EXISTS `Mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mascota` (
  `idMascota` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Raza` varchar(45) NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Especie` varchar(45) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `idCliente_idx` (`idCliente`),
  CONSTRAINT `idCliente` FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mascota`
--

LOCK TABLES `Mascota` WRITE;
/*!40000 ALTER TABLE `Mascota` DISABLE KEYS */;
INSERT INTO `Mascota` VALUES
(1,1,'Lukas','Chusca','Negro','Can',1),
(2,2,'Firu','Hot dog','Marron','Can',1),
(3,2,'Barto','Pitbul','Blanco','Can',1),
(4,3,'Snoopy','Dalmata','Gris','Can',1),
(5,3,'Marte','none','Blanco','Felino',1),
(6,4,'Yerri','none','Marron','Hamster',1);
/*!40000 ALTER TABLE `Mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PerfilUsuario`
--

DROP TABLE IF EXISTS `PerfilUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PerfilUsuario` (
  `idPerfilUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idAccesos` int(11) NOT NULL,
  `NombreUsuario` char(10) NOT NULL,
  `ContraseñaUsuario` char(10) NOT NULL,
  PRIMARY KEY (`idPerfilUsuario`),
  KEY `idAccesos_idx` (`idAccesos`),
  CONSTRAINT `idAccesos` FOREIGN KEY (`idAccesos`) REFERENCES `Accesos` (`idAccesos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PerfilUsuario`
--

LOCK TABLES `PerfilUsuario` WRITE;
/*!40000 ALTER TABLE `PerfilUsuario` DISABLE KEYS */;
INSERT INTO `PerfilUsuario` VALUES
(1,1,'admin','admin123'),
(2,2,'recepe','patas23'),
(3,3,'veter','patitas98');
/*!40000 ALTER TABLE `PerfilUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recepcionista`
--

DROP TABLE IF EXISTS `Recepcionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recepcionista` (
  `idRecepcionista` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  KEY `idUsuario_idx` (`idUsuario`),
  CONSTRAINT `idUsuarioR` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recepcionista`
--

LOCK TABLES `Recepcionista` WRITE;
/*!40000 ALTER TABLE `Recepcionista` DISABLE KEYS */;
INSERT INTO `Recepcionista` VALUES
(1,2);
/*!40000 ALTER TABLE `Recepcionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idPerfilUsuario` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `DNI` char(8) NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Telefono` char(9) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idPerfilUsuario_idx` (`idPerfilUsuario`),
  CONSTRAINT `idPerfilUsuario` FOREIGN KEY (`idPerfilUsuario`) REFERENCES `PerfilUsuario` (`idPerfilUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES
(1,1,'Luis','Muñoz','12345678','Los olivos23','gmail@gmail.com','985684532',1),
(2,2,'Andrea','Jimenes','56983269','Los millares','andrea89@gmail.com','963258741',1),
(3,3,'Marcos','Aldines','58256934','Los palomares','veter@gmail.com','963639742',1);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Veterinario`
--

DROP TABLE IF EXISTS `Veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Veterinario` (
  `idVeterinario` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idVeterinario`),
  KEY `idUsuario_idx` (`idUsuario`),
  CONSTRAINT `idUsuarioV` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Veterinario`
--

LOCK TABLES `Veterinario` WRITE;
/*!40000 ALTER TABLE `Veterinario` DISABLE KEYS */;
INSERT INTO `Veterinario` VALUES
(1,3);
/*!40000 ALTER TABLE `Veterinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'BDPatitas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-25 16:56:17
