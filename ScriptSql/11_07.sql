-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: bdpatitas
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `accesos`
--

DROP TABLE IF EXISTS `accesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesos` (
  `id_acceso` int NOT NULL AUTO_INCREMENT,
  `gestion_citas` tinyint(1) NOT NULL,
  `gestion_atencion` tinyint(1) NOT NULL,
  `gestion_admision` tinyint(1) NOT NULL,
  `gestion_seguridad` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_acceso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesos`
--

LOCK TABLES `accesos` WRITE;
/*!40000 ALTER TABLE `accesos` DISABLE KEYS */;
INSERT INTO `accesos` VALUES (1,1,1,1,1),(2,1,0,1,0),(3,0,1,0,0);
/*!40000 ALTER TABLE `accesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `idAdministrador` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  KEY `idUsuario_idx` (`id_usuario`),
  CONSTRAINT `idUsuarioA` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `id_cita` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `idMascota` int NOT NULL,
  `id_veterinario` int NOT NULL,
  `idRecepcionista` int NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_atencion` datetime NOT NULL,
  `pendiente` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_cita`),
  KEY `idCliente_idx` (`idCliente`),
  KEY `idMascota_idx` (`idMascota`),
  KEY `idVeterinario_idx` (`id_veterinario`),
  KEY `idRecepcionista_idx` (`idRecepcionista`),
  CONSTRAINT `idClienteC` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `idMascotaC` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`),
  CONSTRAINT `idRecepcionistaC` FOREIGN KEY (`idRecepcionista`) REFERENCES `recepcionista` (`idRecepcionista`),
  CONSTRAINT `idVeterinarioC` FOREIGN KEY (`id_veterinario`) REFERENCES `veterinario` (`id_veterinario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `DNI` char(8) NOT NULL,
  `Direccion` varchar(60) NOT NULL,
  `Correo` varchar(50) NOT NULL,
  `Telefono` char(9) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `DNI_UNIQUE` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Luis Fernando  ','Quintana ','85632147','Los mangos 145','cta@gmail.com','985632147',1),(8,'Ciro ','Timana','56969696','asddddd','asddddddddd','963265987',1),(16,'chamo','gonzales','52132541','asddddd','dsaaasasd@gmail.com','852369741',1),(17,'chamo','gonzales','45678910','asddddd','dsaaasasd@gmail.com','852369741',1),(18,'Luis','Andres','96325874','asddddd','dsaaasasd@gmail.com','852369741',1),(24,'Xiomara','Vasquez','33303256','asddddd','dsaaasasd@gmail.com','852369741',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico`
--

DROP TABLE IF EXISTS `diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnostico` (
  `id_diagnostico` int NOT NULL AUTO_INCREMENT,
  `idVeterinario` int NOT NULL,
  `Fecha` datetime NOT NULL,
  `Detalles` varchar(100) NOT NULL,
  `id_Historia_Clinica` int NOT NULL,
  PRIMARY KEY (`id_diagnostico`),
  KEY `idVeterinario_idx` (`idVeterinario`),
  KEY `fk_idHistoria` (`id_Historia_Clinica`),
  CONSTRAINT `fk_idHistoria` FOREIGN KEY (`id_Historia_Clinica`) REFERENCES `historia_clinica` (`id_Historia_Clinica`),
  CONSTRAINT `idVeterinario` FOREIGN KEY (`idVeterinario`) REFERENCES `veterinario` (`id_veterinario`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico`
--

LOCK TABLES `diagnostico` WRITE;
/*!40000 ALTER TABLE `diagnostico` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especie`
--

DROP TABLE IF EXISTS `especie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especie` (
  `id_especie` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  PRIMARY KEY (`id_especie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especie`
--

LOCK TABLES `especie` WRITE;
/*!40000 ALTER TABLE `especie` DISABLE KEYS */;
INSERT INTO `especie` VALUES (1,'Mamifero'),(2,'Aves'),(3,'Reptiles'),(4,'Peces'),(5,'Anfibio');
/*!40000 ALTER TABLE `especie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historia_clinica`
--

DROP TABLE IF EXISTS `historia_clinica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historia_clinica` (
  `id_Historia_Clinica` int NOT NULL AUTO_INCREMENT,
  `idMascota` int NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_Historia_Clinica`),
  KEY `idMascota_idx` (`idMascota`),
  CONSTRAINT `idMascota` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historia_clinica`
--

LOCK TABLES `historia_clinica` WRITE;
/*!40000 ALTER TABLE `historia_clinica` DISABLE KEYS */;
/*!40000 ALTER TABLE `historia_clinica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mascota` (
  `idMascota` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `id_raza` int NOT NULL,
  `color` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `id_especie` int NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `idCliente_idx` (`idCliente`),
  KEY `mascota_razaFK` (`id_raza`),
  KEY `mascota_especieFK` (`id_especie`),
  CONSTRAINT `idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `mascota_especieFK` FOREIGN KEY (`id_especie`) REFERENCES `especie` (`id_especie`),
  CONSTRAINT `mascota_razaFK` FOREIGN KEY (`id_raza`) REFERENCES `raza` (`id_raza`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
INSERT INTO `mascota` VALUES (1,8,'Roger',3,'Negro',1,1),(2,24,'Gruño',2,'Marron',1,1),(4,8,'Godines',4,'Gris',1,1);
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_usuario`
--

DROP TABLE IF EXISTS `perfil_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_usuario` (
  `idperfilusuario` int NOT NULL AUTO_INCREMENT,
  `id_acceso` int NOT NULL,
  `nombreusuario` char(10) NOT NULL,
  `contraseñausuario` char(10) NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`idperfilusuario`),
  KEY `idAccesos_idx` (`id_acceso`),
  KEY `PerfilUsuario_FK` (`id_usuario`),
  CONSTRAINT `idAccesos` FOREIGN KEY (`id_acceso`) REFERENCES `accesos` (`id_acceso`),
  CONSTRAINT `PerfilUsuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_usuario`
--

LOCK TABLES `perfil_usuario` WRITE;
/*!40000 ALTER TABLE `perfil_usuario` DISABLE KEYS */;
INSERT INTO `perfil_usuario` VALUES (1,1,'admin','admin123',1),(2,2,'recepe','patas23',2),(3,3,'veter','patitas98',3),(4,1,'Elios','admin123',5);
/*!40000 ALTER TABLE `perfil_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raza`
--

DROP TABLE IF EXISTS `raza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raza` (
  `id_raza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_raza`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raza`
--

LOCK TABLES `raza` WRITE;
/*!40000 ALTER TABLE `raza` DISABLE KEYS */;
INSERT INTO `raza` VALUES (1,'Mudi'),(2,'Pastor islandés'),(3,'Perro lobo'),(4,'Pastor aleman'),(5,'Shih tzu'),(6,'Peces');
/*!40000 ALTER TABLE `raza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recepcionista`
--

DROP TABLE IF EXISTS `recepcionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recepcionista` (
  `idRecepcionista` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  KEY `idUsuario_idx` (`id_usuario`),
  CONSTRAINT `idUsuarioR` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recepcionista`
--

LOCK TABLES `recepcionista` WRITE;
/*!40000 ALTER TABLE `recepcionista` DISABLE KEYS */;
INSERT INTO `recepcionista` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `recepcionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `DNI` char(8) NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Correo` varchar(45) NOT NULL,
  `Telefono` char(9) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Luis','Muñoz','12345678','Los olivos23','gmail@gmail.com','985684532',1),(2,'Andrea','Jimenes','56983269','Los millares','andrea89@gmail.com','963258741',1),(3,'Marcos','Aldines','58256934','Los palomares','veter@gmail.com','963639742',1),(4,'Ciro ','asdddd','55555555','asddddd','saddddd@','636363636',1),(5,'dsaaaa','asdddd','78945612','asdddd','dsaaasasd@gmail.com','963258741',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinario`
--

DROP TABLE IF EXISTS `veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinario` (
  `id_veterinario` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_veterinario`),
  KEY `idUsuario_idx` (`id_usuario`),
  CONSTRAINT `idUsuarioV` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinario`
--

LOCK TABLES `veterinario` WRITE;
/*!40000 ALTER TABLE `veterinario` DISABLE KEYS */;
INSERT INTO `veterinario` VALUES (1,3);
/*!40000 ALTER TABLE `veterinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bdpatitas'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar`(IN nmUsuario VARCHAR(60)
	,IN cnUsurio VARCHAR(50), IN acceso INT , IN idUsuario INT)
BEGIN	
		SET @idperfil = (SELECT idperfilusuario from perfil_usuario where nombreusuario = nmUsuario);
		UPDATE perfil_usuario 
		SET nombreusuario = nmUsuario,contraseñausuario  = cnUsurio,
		id_acceso = acceso, id_usuario = idUsuario 
		WHERE idperfilusuario  = @idperfil;
		
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listarPerfilesPorIdUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarPerfilesPorIdUsuario`(IN _idalumno INT)
BEGIN	
	SELECT idperfilusuario, nombreusuario , contraseñausuario
	FROM perfil_usuario
	WHERE id_usuario = _idalumno;
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_MantRegistrarCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_MantRegistrarCliente`(IN nombre VARCHAR(45), IN apellidos VARCHAR(45), IN dni char(8), IN direccion VARCHAR(60), IN correo VARCHAR(50), IN telefono CHAR(9),
IN estado tinyint(1))
BEGIN
	INSERT INTO Cliente (idCliente, Nombre, Apellidos, DNI, Direccion, Correo, Telefono, Estado) VALUES (idcliente,nombre,apellidos,dni,direccion,correo,telefono,estado);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_MantRegistrarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_MantRegistrarUsuario`(IN Nombre VARCHAR(45), IN Apellidos VARCHAR(45), IN DNI Char(8), IN Direccion VARCHAR(60), IN Email VARCHAR(45), Telefono CHAR(9), IN Estado tinyint(1))
BEGIN
	SET @idusuario = (SELECT MAX(id_usuario)+1 FROM usuario);
	INSERT INTO usuario (id_usuario, Nombre, Apellidos, DNI, Direccion, Correo, Telefono, Estado) VALUES (@idusuario,Nombre,Apellidos,DNI,Direccion,Email ,Telefono,Estado);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_nuevoPerfil` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_nuevoPerfil`(IN nmUsuario VARCHAR(60)
	,IN cnUsurio VARCHAR(50), IN acceso INT , IN idUsuario INT)
BEGIN	
		SET @idPerfil = (SELECT MAX(idperfilusuario)+1 FROM perfil_usuario);
		INSERT INTO perfil_usuario VALUES(@idPerfil, acceso.
			nmUsuario,cnUsurio,  idUsuario );
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_registrarCita` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrarCita`(IN idCliente INT, IN idMascota INT, IN idVeterinario INT, IN idRecep INT,
		IN fecha_registro DATETIME, IN fecha_atencion DATETIME, in estado tinyint(1))
BEGIN	
	set  @idCita = (select max(id_cita) + 1  from cita);
	insert into cita (id_cita, idCliente, idMascota, id_veterinario, idRecepcionista, fecha_registro, fecha_atencion, pendiente)
	values(@idCita,idCliente, idMascota,  idVeterinario,idRecep, fecha_registro, fecha_atencion, estado );
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_registrarDiagnostico` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrarDiagnostico`(IN idVet INT,IN fecha DATETIME, In detalles VARCHAR(150) , IN idHistoria INT)
BEGIN
	SET @idDiagnostico = (SELECT MAX(id_diagnostico) + 1  FROM diagnostico);
	/*SET @idVet = (SELECT v.id_veterinario from veterinario v INNER JOIN usuario u ON v.id_usuario = u.id_usuario
			where u.Nombre = @nomVet);*/
	INSERT INTO diagnostico(id_diagnostico, idVeterinario, Fecha,Detalles,id_Historia_Clinica) 
	VALUES(@idDiagnostico ,idVet, fecha, detalles, idHistoria);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_registrarMascota` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrarMascota`(IN idCliente INT, IN nombre VARCHAR(60), IN id_raza INT, IN color VARCHAR(60),
		in id_especie INT, in estado tinyint(1))
BEGIN	
	set  @idMascota = (select max(idMascota) + 1  from mascota);
	insert into mascota (idMascota, idCliente, nombre, id_raza, color, id_especie, estado)
	values(@idMascota,idCliente, nombre,  id_raza,color, id_especie, estado );
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-11 23:13:50
