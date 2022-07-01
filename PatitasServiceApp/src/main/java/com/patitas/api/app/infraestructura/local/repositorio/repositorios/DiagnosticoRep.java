package com.patitas.api.app.infraestructura.local.repositorio.repositorios;

import java.util.Calendar;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.DiagnosticoJpa;

@Repository
public interface DiagnosticoRep extends JpaRepository<DiagnosticoJpa, Integer>{
	@Transactional
	@Modifying
	@Query(value = "{call sp_registrarDiagnostico(:idVet, :fecha, :detalles, :idHistoria)}", nativeQuery = true )
	void registrarDiagnostico(@Param("idVet") Integer idVet, 
			@Param("fecha") Calendar fecha,
			@Param("detalles") String detalles,
			@Param("idHistoria") Integer idHis);
}
