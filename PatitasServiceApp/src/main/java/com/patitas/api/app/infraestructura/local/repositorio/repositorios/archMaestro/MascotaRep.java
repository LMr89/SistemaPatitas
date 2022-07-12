package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.ClienteJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;

@Repository
public interface MascotaRep extends JpaRepository<MascotaJpa, Integer> {
	@Transactional
	@Modifying
	@Query(value = "{call sp_registrarMascota(:idCliente, :nombre, :id_raza, :color,"
			+ ":id_especie, :estado )}", nativeQuery = true) 
	void registrarMascota(
			@Param("idCliente") Integer  cliente,
			@Param("nombre") String nombre,
			@Param("id_raza") Integer idRaza,
			@Param("color") String color,
		@Param("id_especie") Integer idEspecie,
		@Param("estado") Boolean estado);
	
	
	Optional<MascotaJpa> findByIdCliente(ClienteJpa idCliente);

}
