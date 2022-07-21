package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.AdministradorJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;

@Repository
public interface AdministradorRep extends JpaRepository<AdministradorJpa,  Integer> {
	Optional<AdministradorJpa> findByIdUsuario(UsuarioJpa idUsuario);

}
