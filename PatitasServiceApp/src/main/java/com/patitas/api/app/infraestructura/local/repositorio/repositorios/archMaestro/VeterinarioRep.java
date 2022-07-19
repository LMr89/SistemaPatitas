package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;

public interface VeterinarioRep extends JpaRepository<VeterinarioJpa, Integer> {
	Optional<VeterinarioJpa> findByIdUsu(UsuarioJpa idUsu);

}
