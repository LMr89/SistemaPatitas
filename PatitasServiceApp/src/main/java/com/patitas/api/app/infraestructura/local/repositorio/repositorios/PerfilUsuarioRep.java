package com.patitas.api.app.infraestructura.local.repositorio.repositorios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.PerfilUsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.UsuarioJpa;

@Repository
public interface PerfilUsuarioRep extends  JpaRepository<PerfilUsuarioJpa, Integer>{
	Optional<PerfilUsuarioJpa> findByNombreUsuarioAndContraseniaUsuario(String nombre, 
			String contrasenia);
	Optional<PerfilUsuarioJpa> findByUsuarioEquals(UsuarioJpa us);
	Optional<PerfilUsuarioJpa> findByUsuarioEquals(Integer IdUs);
	
	@Query(value = "{call sp_listarPerfilesPorIdUsuario(:_idalumno)}",nativeQuery = true)
	PerfilUsuarioJpa obtenerPerfilPorIdUsuario(@Param("_idalumno") Integer idAlumno);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_nuevoPerfil(:nmUsuario, :cnUsurio, :acceso, :idUsuario)}", nativeQuery = true) 
	void registrarNuevoPerfil(
			@Param("nmUsuario") String  user,
			@Param("cnUsurio") String contraseña,
			@Param("acceso") Integer idAcceso,
			@Param("idUsuario") Integer idUsuario);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_actualizar(:nmUsuario, :cnUsurio, :acceso, :idUsuario)}", nativeQuery = true) 
	void actualizarPerfil(
			@Param("nmUsuario") String  user,
			@Param("cnUsurio") String contraseña,
			@Param("acceso") Integer idAcceso,
			@Param("idUsuario") Integer idUsuario);
	
	
	
}
