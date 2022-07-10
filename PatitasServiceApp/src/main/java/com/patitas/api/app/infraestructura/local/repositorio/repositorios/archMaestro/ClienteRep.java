package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.ClienteJpa;

@Repository
public interface ClienteRep extends JpaRepository<ClienteJpa, Integer> {
	@Transactional
	@Modifying
	@Query(value = "{call sp_MantRegistrarCliente(:nombre, :apellidos, :dni, :direccion, :correo, :telefono, :estado)}", nativeQuery = true )
	void registrarCliente(@Param("nombre") String nombre, 
			@Param("apellidos") String apellidos,
			@Param("dni") String dni,
			@Param("direccion") String direccion,
			@Param("correo") String correo,
			@Param("telefono") String telefono,
			@Param("estado") Boolean estado);

}
