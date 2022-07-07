-- MariaDB dump 10.19  Distrib 10.8.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: BDPatitas
-- ------------------------------------------------------
-- Server version	10.8.3-MariaDB

use BDPatitas;

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
  CONSTRAINT `idUsuarioA` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
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
  CONSTRAINT `idClienteC` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idMascotaC` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idRecepcionistaC` FOREIGN KEY (`idRecepcionista`) REFERENCES `Recepcionista` (`idRecepcionista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idVeterinarioC` FOREIGN KEY (`idVeterinario`) REFERENCES `veterinario` (`id_veterinario`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `idUsuarioR` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recepcionista`
--

LOCK TABLES `Recepcionista` WRITE;
/*!40000 ALTER TABLE `Recepcionista` DISABLE KEYS */;
/*!40000 ALTER TABLE `Recepcionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accesos`
--

DROP TABLE IF EXISTS `accesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accesos` (
  `id_acceso` int(11) NOT NULL AUTO_INCREMENT,
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
INSERT INTO `accesos` VALUES
(1,1,1,1,1),
(2,1,0,1,0),
(3,0,1,0,0);
/*!40000 ALTER TABLE `accesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES
(1,'Luis Fernando  ','Quintana ','85632147','Los mangos 145','cta@gmail.com','985632147',1),
(8,'Ciro ','Timana','56969696','asddddd','asddddddddd','963265987',1),
(16,'chamo','gonzales','52132541','asddddd','dsaaasasd@gmail.com','852369741',1),
(17,'chamo','gonzales','45678910','asddddd','dsaaasasd@gmail.com','852369741',1),
(18,'Luis','Andres','96325874','asddddd','dsaaasasd@gmail.com','852369741',1),
(24,'Xiomara','Vasquez','33303256','asddddd','dsaaasasd@gmail.com','852369741',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico`
--

DROP TABLE IF EXISTS `diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostico` (
  `id_diagnostico` int(11) NOT NULL AUTO_INCREMENT,
  `idVeterinario` int(11) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Detalles` varchar(100) NOT NULL,
  `id_Historia_Clinica` int(11) NOT NULL,
  PRIMARY KEY (`id_diagnostico`),
  KEY `idVeterinario_idx` (`idVeterinario`),
  KEY `fk_idHistoria` (`id_Historia_Clinica`),
  CONSTRAINT `fk_idHistoria` FOREIGN KEY (`id_Historia_Clinica`) REFERENCES `historia_clinica` (`id_Historia_Clinica`),
  CONSTRAINT `idVeterinario` FOREIGN KEY (`idVeterinario`) REFERENCES `veterinario` (`id_veterinario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico`
--

LOCK TABLES `diagnostico` WRITE;
/*!40000 ALTER TABLE `diagnostico` DISABLE KEYS */;
INSERT INTO `diagnostico` VALUES
(29,1,'2022-06-27 18:10:17','asdsadasdadasdsa',1);
/*!40000 ALTER TABLE `diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historia_clinica`
--

DROP TABLE IF EXISTS `historia_clinica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historia_clinica` (
  `id_Historia_Clinica` int(11) NOT NULL AUTO_INCREMENT,
  `idMascota` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_Historia_Clinica`),
  KEY `idMascota_idx` (`idMascota`),
  CONSTRAINT `idMascota` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historia_clinica`
--

LOCK TABLES `historia_clinica` WRITE;
/*!40000 ALTER TABLE `historia_clinica` DISABLE KEYS */;
INSERT INTO `historia_clinica` VALUES
(1,1,1);
/*!40000 ALTER TABLE `historia_clinica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mascota` (
  `idMascota` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Raza` varchar(45) NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Especie` varchar(45) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `idCliente_idx` (`idCliente`),
  CONSTRAINT `idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
INSERT INTO `mascota` VALUES
(1,1,'Lukas','Chusca','Negro','Can',1);
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_usuario`
--

DROP TABLE IF EXISTS `perfil_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil_usuario` (
  `idperfilusuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_acceso` int(11) NOT NULL,
  `nombreusuario` char(10) NOT NULL,
  `contraseñausuario` char(10) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`idperfilusuario`),
  KEY `idAccesos_idx` (`id_acceso`),
  KEY `PerfilUsuario_FK` (`id_usuario`),
  CONSTRAINT `PerfilUsuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `idAccesos` FOREIGN KEY (`id_acceso`) REFERENCES `accesos` (`id_acceso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_usuario`
--

LOCK TABLES `perfil_usuario` WRITE;
/*!40000 ALTER TABLE `perfil_usuario` DISABLE KEYS */;
INSERT INTO `perfil_usuario` VALUES
(1,1,'admin','admin123',1),
(2,2,'recepe','patas23',2),
(3,3,'veter','patitas98',3),
(4,1,'Elios','admin123',5);
/*!40000 ALTER TABLE `perfil_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
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
INSERT INTO `usuario` VALUES
(1,'Luis','Muñoz','12345678','Los olivos23','gmail@gmail.com','985684532',1),
(2,'Andrea','Jimenes','56983269','Los millares','andrea89@gmail.com','963258741',1),
(3,'Marcos','Aldines','58256934','Los palomares','veter@gmail.com','963639742',1),
(4,'Ciro ','asdddd','55555555','asddddd','saddddd@','636363636',1),
(5,'dsaaaa','asdddd','78945612','asdddd','dsaaasasd@gmail.com','963258741',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinario`
--

DROP TABLE IF EXISTS `veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veterinario` (
  `id_veterinario` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_veterinario`),
  KEY `idUsuario_idx` (`id_usuario`),
  CONSTRAINT `idUsuarioV` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinario`
--

LOCK TABLES `veterinario` WRITE;
/*!40000 ALTER TABLE `veterinario` DISABLE KEYS */;
INSERT INTO `veterinario` VALUES
(1,3);
/*!40000 ALTER TABLE `veterinario` ENABLE KEYS */;
UNLOCK TABLES;

-
DELIMITER $$
CREATE PROCEDURE sp_MantRegistrarUsuario(IN Nombre VARCHAR(45), IN Apellidos VARCHAR(45), IN DNI Char(8), IN Direccion VARCHAR(60), IN Email VARCHAR(45), Telefono CHAR(9), IN Estado tinyint(1))
BEGIN
	SET @idusuario = (SELECT MAX(id_usuario)+1 FROM usuario);
	INSERT INTO usuario (id_usuario, Nombre, Apellidos, DNI, Direccion, Correo, Telefono, Estado) VALUES (@idusuario,Nombre,Apellidos,DNI,Direccion,Email ,Telefono,Estado);
END$$
DELIMITER

DELIMITER $$
CREATE PROCEDURE sp_MantRegistrarCliente(IN nombre VARCHAR(45), IN apellidos VARCHAR(45), IN dni char(8), IN direccion VARCHAR(60), IN correo VARCHAR(50), IN telefono CHAR(9),
IN estado tinyint(1))
BEGIN
	INSERT INTO Cliente (idCliente, Nombre, Apellidos, DNI, Direccion, Correo, Telefono, Estado) VALUES (idcliente,nombre,apellidos,dni,direccion,correo,telefono,estado);
END$$
DELIMITER

DELIMITER $$
CREATE PROCEDURE sp_registrarDiagnostico(IN idVet INT,IN fecha DATETIME, In detalles VARCHAR(150) , IN idHistoria INT)
BEGIN
	SET @idDiagnostico = (SELECT MAX(id_diagnostico) + 1  FROM diagnostico);
	/*SET @idVet = (SELECT v.id_veterinario from veterinario v INNER JOIN usuario u ON v.id_usuario = u.id_usuario
			where u.Nombre = @nomVet);*/
	INSERT INTO diagnostico(id_diagnostico, idVeterinario, Fecha,Detalles,id_Historia_Clinica) 
	VALUES(@idDiagnostico ,idVet, fecha, detalles, idHistoria);
END$$
DELIMITER

DELIMITER %%
CREATE  PROCEDURE sp_nuevoPerfil(IN nmUsuario VARCHAR(60)
	,IN cnUsurio VARCHAR(50), IN acceso INT , IN idUsuario INT)
	BEGIN	
		SET @idPerfil = (SELECT MAX(idperfilusuario)+1 FROM perfil_usuario);
		INSERT INTO perfil_usuario VALUES(@idPerfil, acceso.
			nmUsuario,cnUsurio,  idUsuario );
	END%%
DELIMITER
DELIMITER %%
CREATE  PROCEDURE sp_actualizar(IN nmUsuario VARCHAR(60)
	,IN cnUsurio VARCHAR(50), IN acceso INT , IN idUsuario INT)
	BEGIN	
		SET @idperfil = (SELECT idperfilusuario from perfil_usuario where nombreusuario = nmUsuario);
		UPDATE perfil_usuario 
		SET nombreusuario = nmUsuario,contraseñausuario  = cnUsurio,
		id_acceso = acceso, id_usuario = idUsuario 
		WHERE idperfilusuario  = @idperfil;
		
	END%%
DELIMITER	

DELIMITER %%
CREATE  PROCEDURE sp_listarPerfilesPorIdUsuario(IN _idalumno INT)
BEGIN	
	SELECT idperfilusuario, nombreusuario , contraseñausuario
	FROM perfil_usuario
	WHERE id_usuario = _idalumno;
	END%%
