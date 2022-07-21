package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HorarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;

@Repository
public interface HorarioRep extends JpaRepository<HorarioJpa, Integer> {

	Optional<HorarioJpa> findByIdVet(VeterinarioJpa idVet);


	
	@Transactional
	@Modifying
	@Query(value = "{call sp_registrarHorario(:idUsuario, :manInicio, :manFin , :tarInicio, :tarFin)}", nativeQuery = true)
	void registrarHorario(
			@Param("idUsuario") Integer  cliente,
			@Param("manInicio") String manInicio,
			@Param("manFin") String manFin,
			@Param("tarInicio") String tarInicio,
			@Param("tarFin") String tarFin);


}
