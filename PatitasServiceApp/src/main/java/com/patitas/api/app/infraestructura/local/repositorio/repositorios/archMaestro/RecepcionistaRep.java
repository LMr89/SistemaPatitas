package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.RecepcionistaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;

public interface RecepcionistaRep extends JpaRepository<RecepcionistaJpa, Integer>{
	Optional<RecepcionistaJpa> findByIdUsuario(UsuarioJpa idUsuario);

}
