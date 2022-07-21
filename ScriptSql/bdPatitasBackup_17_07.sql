-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdpatitas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdpatitas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdpatitas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bdpatitas` ;

-- -----------------------------------------------------
-- Table `bdpatitas`.`accesos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`accesos` (
  `id_acceso` INT NOT NULL AUTO_INCREMENT,
  `gestion_citas` TINYINT(1) NOT NULL,
  `gestion_atencion` TINYINT(1) NOT NULL,
  `gestion_admision` TINYINT(1) NOT NULL,
  `gestion_seguridad` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_acceso`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `DNI` CHAR(8) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(45) NOT NULL,
  `Telefono` CHAR(9) NOT NULL,
  `Estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`administrador` (
  `idAdministrador` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  INDEX `idUsuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `idUsuarioA`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdpatitas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `DNI` CHAR(8) NOT NULL,
  `Direccion` VARCHAR(60) NOT NULL,
  `Correo` VARCHAR(50) NOT NULL,
  `Telefono` CHAR(9) NOT NULL,
  `Estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `DNI_UNIQUE` (`DNI` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`especie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`especie` (
  `id_especie` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id_especie`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdpatitas`.`raza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`raza` (
  `id_raza` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_raza`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdpatitas`.`mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`mascota` (
  `idMascota` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `id_raza` INT NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `id_especie` INT NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idMascota`),
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  INDEX `mascota_razaFK` (`id_raza` ASC) VISIBLE,
  INDEX `mascota_especieFK` (`id_especie` ASC) VISIBLE,
  CONSTRAINT `idCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bdpatitas`.`cliente` (`idCliente`),
  CONSTRAINT `mascota_especieFK`
    FOREIGN KEY (`id_especie`)
    REFERENCES `bdpatitas`.`especie` (`id_especie`),
  CONSTRAINT `mascota_razaFK`
    FOREIGN KEY (`id_raza`)
    REFERENCES `bdpatitas`.`raza` (`id_raza`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`recepcionista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`recepcionista` (
  `idRecepcionista` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  INDEX `idUsuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `idUsuarioR`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdpatitas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`veterinario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`veterinario` (
  `id_veterinario` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_veterinario`),
  INDEX `idUsuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `idUsuarioV`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdpatitas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`cita` (
  `id_cita` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idMascota` INT NOT NULL,
  `id_veterinario` INT NOT NULL,
  `idRecepcionista` INT NOT NULL,
  `fecha_registro` DATETIME NOT NULL,
  `fecha_atencion` DATETIME NOT NULL,
  `pendiente` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_cita`),
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  INDEX `idMascota_idx` (`idMascota` ASC) VISIBLE,
  INDEX `idVeterinario_idx` (`id_veterinario` ASC) VISIBLE,
  INDEX `idRecepcionista_idx` (`idRecepcionista` ASC) VISIBLE,
  CONSTRAINT `idClienteC`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bdpatitas`.`cliente` (`idCliente`),
  CONSTRAINT `idMascotaC`
    FOREIGN KEY (`idMascota`)
    REFERENCES `bdpatitas`.`mascota` (`idMascota`),
  CONSTRAINT `idRecepcionistaC`
    FOREIGN KEY (`idRecepcionista`)
    REFERENCES `bdpatitas`.`recepcionista` (`idRecepcionista`),
  CONSTRAINT `idVeterinarioC`
    FOREIGN KEY (`id_veterinario`)
    REFERENCES `bdpatitas`.`veterinario` (`id_veterinario`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`historia_clinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`historia_clinica` (
  `id_Historia_Clinica` INT NOT NULL AUTO_INCREMENT,
  `idMascota` INT NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `Estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_Historia_Clinica`),
  INDEX `idMascota_idx` (`idMascota` ASC) VISIBLE,
  CONSTRAINT `idMascota`
    FOREIGN KEY (`idMascota`)
    REFERENCES `bdpatitas`.`mascota` (`idMascota`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`diagnostico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`diagnostico` (
  `id_diagnostico` INT NOT NULL AUTO_INCREMENT,
  `idVeterinario` INT NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Detalles` VARCHAR(100) NOT NULL,
  `id_Historia_Clinica` INT NOT NULL,
  `medicamentos` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id_diagnostico`),
  INDEX `idVeterinario_idx` (`idVeterinario` ASC) VISIBLE,
  INDEX `fk_idHistoria` (`id_Historia_Clinica` ASC) VISIBLE,
  CONSTRAINT `fk_idHistoria`
    FOREIGN KEY (`id_Historia_Clinica`)
    REFERENCES `bdpatitas`.`historia_clinica` (`id_Historia_Clinica`),
  CONSTRAINT `idVeterinario`
    FOREIGN KEY (`idVeterinario`)
    REFERENCES `bdpatitas`.`veterinario` (`id_veterinario`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`horario` (
  `id_horario` INT NOT NULL AUTO_INCREMENT,
  `id_veterinario` INT NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `hora_fin` TIME NOT NULL,
  PRIMARY KEY (`id_horario`),
  INDEX `Horario_FK` (`id_veterinario` ASC) VISIBLE,
  CONSTRAINT `Horario_FK`
    FOREIGN KEY (`id_veterinario`)
    REFERENCES `bdpatitas`.`veterinario` (`id_veterinario`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdpatitas`.`perfil_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdpatitas`.`perfil_usuario` (
  `idperfilusuario` INT NOT NULL AUTO_INCREMENT,
  `id_acceso` INT NOT NULL,
  `nombreusuario` CHAR(10) NOT NULL,
  `contraseñausuario` CHAR(10) NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idperfilusuario`),
  INDEX `idAccesos_idx` (`id_acceso` ASC) VISIBLE,
  INDEX `PerfilUsuario_FK` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `idAccesos`
    FOREIGN KEY (`id_acceso`)
    REFERENCES `bdpatitas`.`accesos` (`id_acceso`),
  CONSTRAINT `PerfilUsuario_FK`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdpatitas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;

USE `bdpatitas` ;

-- -----------------------------------------------------
-- procedure sp_MantRegistrarCliente
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_MantRegistrarCliente`(IN nombre VARCHAR(45), IN apellidos VARCHAR(45), IN dni char(8), IN direccion VARCHAR(60), IN correo VARCHAR(50), IN telefono CHAR(9),
IN estado tinyint(1))
BEGIN
	INSERT INTO Cliente (idCliente, Nombre, Apellidos, DNI, Direccion, Correo, Telefono, Estado) VALUES (idcliente,nombre,apellidos,dni,direccion,correo,telefono,estado);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_MantRegistrarUsuario
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_MantRegistrarUsuario`(IN Nombre VARCHAR(45), IN Apellidos VARCHAR(45), IN DNI Char(8), IN Direccion VARCHAR(60), IN Email VARCHAR(45), Telefono CHAR(9), IN Estado tinyint(1))
BEGIN
	SET @idusuario = (SELECT MAX(id_usuario)+1 FROM usuario);
	INSERT INTO usuario (id_usuario, Nombre, Apellidos, DNI, Direccion, Correo, Telefono, Estado) VALUES (@idusuario,Nombre,Apellidos,DNI,Direccion,Email ,Telefono,Estado);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_actualizar
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar`(IN nmUsuario VARCHAR(60)
	,IN cnUsurio VARCHAR(50), IN acceso INT , IN idUsuario INT)
BEGIN	
		SET @idperfil = (SELECT idperfilusuario from perfil_usuario where nombreusuario = nmUsuario);
		UPDATE perfil_usuario 
		SET nombreusuario = nmUsuario,contraseñausuario  = cnUsurio,
		id_acceso = acceso, id_usuario = idUsuario 
		WHERE idperfilusuario  = @idperfil;
		
	END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_listarPerfilesPorIdUsuario
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarPerfilesPorIdUsuario`(IN _idalumno INT)
BEGIN	
	SELECT idperfilusuario, nombreusuario , contraseñausuario
	FROM perfil_usuario
	WHERE id_usuario = _idalumno;
	END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_nuevoPerfil
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_nuevoPerfil`(IN nmUsuario VARCHAR(60)
	,IN cnUsurio VARCHAR(50), IN acceso INT , IN idUsuario INT)
BEGIN	
		SET @idPerfil = (SELECT MAX(idperfilusuario)+1 FROM perfil_usuario);
		INSERT INTO perfil_usuario VALUES(@idPerfil, acceso.
			nmUsuario,cnUsurio,  idUsuario );
	END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_registrarCita
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrarCita`(IN idCliente INT, IN idMascota INT, IN idVeterinario INT, IN idRecep INT,
		IN fecha_registro DATETIME, IN fecha_atencion DATETIME, in estado tinyint(1))
BEGIN	
	set  @idCita = (select max(id_cita) + 1  from cita);
	insert into cita (id_cita, idCliente, idMascota, id_veterinario, idRecepcionista, fecha_registro, fecha_atencion, pendiente)
	values(@idCita,idCliente, idMascota,  idVeterinario,idRecep, fecha_registro, fecha_atencion, estado );
	END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_registrarDiagnostico
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrarDiagnostico`(IN idVet INT,IN fecha DATETIME, In detalles VARCHAR(150) , IN idHistoria INT)
BEGIN
	SET @idDiagnostico = (SELECT MAX(id_diagnostico) + 1  FROM diagnostico);
	/*SET @idVet = (SELECT v.id_veterinario from veterinario v INNER JOIN usuario u ON v.id_usuario = u.id_usuario
			where u.Nombre = @nomVet);*/
	INSERT INTO diagnostico(id_diagnostico, idVeterinario, Fecha,Detalles,id_Historia_Clinica) 
	VALUES(@idDiagnostico ,idVet, fecha, detalles, idHistoria);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_registrarMascota
-- -----------------------------------------------------

DELIMITER $$
USE `bdpatitas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrarMascota`(IN idCliente INT, IN nombre VARCHAR(60), IN id_raza INT, IN color VARCHAR(60),
		in id_especie INT, in estado tinyint(1))
BEGIN	
	set  @idMascota = (select max(idMascota) + 1  from mascota);
	insert into mascota (idMascota, idCliente, nombre, id_raza, color, id_especie, estado)
	values(@idMascota,idCliente, nombre,  id_raza,color, id_especie, estado );
	END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
