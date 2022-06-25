-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BDPatitas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BDPatitas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BDPatitas` DEFAULT CHARACTER SET utf8 ;
USE `BDPatitas` ;

-- -----------------------------------------------------
-- Table `BDPatitas`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `DNI` CHAR(8) NOT NULL,
  `Direccion` VARCHAR(60) NOT NULL,
  `Correo` VARCHAR(50) NOT NULL,
  `Telefono` CHAR(9) NOT NULL,
  `Estado` BIT NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `DNI_UNIQUE` (`DNI` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Mascota` (
  `idMascota` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Raza` VARCHAR(45) NOT NULL,
  `Color` VARCHAR(45) NOT NULL,
  `Especie` VARCHAR(45) NOT NULL,
  `Estado` BIT NOT NULL,
  PRIMARY KEY (`idMascota`),
  INDEX `idCliente_idx` (`idCliente` ASC),
  CONSTRAINT `idCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `BDPatitas`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Accesos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Accesos` (
  `idAccesos` INT NOT NULL AUTO_INCREMENT,
  `GestionCitas` INT NOT NULL,
  `GestionAtencionV` BIT NOT NULL,
  `GestionAdmision` BIT NOT NULL,
  `GestionSeguridad` BIT NOT NULL,
  PRIMARY KEY (`idAccesos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`PerfilUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`PerfilUsuario` (
  `idPerfilUsuario` INT NOT NULL AUTO_INCREMENT,
  `idAccesos` INT NOT NULL,
  `NombreUsuario` CHAR(10) NOT NULL,
  `ContraseñaUsuario` CHAR(10) NOT NULL,
  PRIMARY KEY (`idPerfilUsuario`),
  INDEX `idAccesos_idx` (`idAccesos` ASC),
  CONSTRAINT `idAccesos`
    FOREIGN KEY (`idAccesos`)
    REFERENCES `BDPatitas`.`Accesos` (`idAccesos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `idPerfilUsuario` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `DNI` CHAR(8) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefono` CHAR(9) NOT NULL,
  `Estado` BIT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `idPerfilUsuario_idx` (`idPerfilUsuario` ASC),
  CONSTRAINT `idPerfilUsuario`
    FOREIGN KEY (`idPerfilUsuario`)
    REFERENCES `BDPatitas`.`PerfilUsuario` (`idPerfilUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Veterinario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Veterinario` (
  `idVeterinario` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idVeterinario`),
  INDEX `idUsuario_idx` (`idUsuario` ASC),
  CONSTRAINT `idUsuarioV`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `BDPatitas`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Diagnostico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Diagnostico` (
  `idDiagnostico` INT NOT NULL AUTO_INCREMENT,
  `idVeterinario` INT NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Detalles` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idDiagnostico`),
  INDEX `idVeterinario_idx` (`idVeterinario` ASC),
  CONSTRAINT `idVeterinario`
    FOREIGN KEY (`idVeterinario`)
    REFERENCES `BDPatitas`.`Veterinario` (`idVeterinario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`HistoriaClinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`HistoriaClinica` (
  `idHistoriaClinica` INT NOT NULL AUTO_INCREMENT,
  `idMascota` INT NOT NULL,
  `idDiagnostico` INT NOT NULL,
  `Estado` BIT NOT NULL,
  PRIMARY KEY (`idHistoriaClinica`),
  INDEX `idMascota_idx` (`idMascota` ASC),
  INDEX `idDiagnostico_idx` (`idDiagnostico` ASC),
  CONSTRAINT `idMascota`
    FOREIGN KEY (`idMascota`)
    REFERENCES `BDPatitas`.`Mascota` (`idMascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idDiagnostico`
    FOREIGN KEY (`idDiagnostico`)
    REFERENCES `BDPatitas`.`Diagnostico` (`idDiagnostico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Recepcionista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Recepcionista` (
  `idRecepcionista` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  INDEX `idUsuario_idx` (`idUsuario` ASC),
  CONSTRAINT `idUsuarioR`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `BDPatitas`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Cita` (
  `idCita` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idMascota` INT NOT NULL,
  `idVeterinario` INT NOT NULL,
  `idRecepcionista` INT NOT NULL,
  `FechaRegistro` DATETIME NOT NULL,
  `FechaAtencion` DATETIME NOT NULL,
  `Pendiente` BIT NOT NULL,
  PRIMARY KEY (`idCita`),
  INDEX `idCliente_idx` (`idCliente` ASC),
  INDEX `idMascota_idx` (`idMascota` ASC),
  INDEX `idVeterinario_idx` (`idVeterinario` ASC),
  INDEX `idRecepcionista_idx` (`idRecepcionista` ASC),
  CONSTRAINT `idClienteC`
    FOREIGN KEY (`idCliente`)
    REFERENCES `BDPatitas`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idMascotaC`
    FOREIGN KEY (`idMascota`)
    REFERENCES `BDPatitas`.`Mascota` (`idMascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idVeterinarioC`
    FOREIGN KEY (`idVeterinario`)
    REFERENCES `BDPatitas`.`Veterinario` (`idVeterinario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idRecepcionistaC`
    FOREIGN KEY (`idRecepcionista`)
    REFERENCES `BDPatitas`.`Recepcionista` (`idRecepcionista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDPatitas`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDPatitas`.`Administrador` (
  `idAdministrador` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  INDEX `idUsuario_idx` (`idUsuario` ASC),
  CONSTRAINT `idUsuarioA`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `BDPatitas`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
















