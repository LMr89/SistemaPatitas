package com.patitas.api.app.infraestructura.local.repositorio.repositorios.cita;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.cita.CitaJpa;

@Repository
public interface CitaRep extends JpaRepository<CitaJpa, Integer> {
	@Transactional
	@Modifying
	@Query(value = "{call sp_registrarCita(:idCliente, :idMascota, :idVeterinario, :idRecep,"
			+ ":fecha_registro, :fecha_atencion, :estado )}", nativeQuery = true) 
	void registrarCita(
			@Param("idCliente") Integer  cliente,
			@Param("idMascota") Integer mascota,
			@Param("idVeterinario") Integer veterinario,
			@Param("idRecep") Integer recepcionista,
		@Param("fecha_registro") Calendar fecha_registro,
		@Param("fecha_atencion") Calendar fecha_atencion,
		@Param("estado") Boolean estado);

}
