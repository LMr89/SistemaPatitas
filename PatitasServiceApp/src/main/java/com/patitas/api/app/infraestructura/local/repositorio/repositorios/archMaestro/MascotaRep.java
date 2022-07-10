package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;

public interface MascotaRep extends JpaRepository<MascotaJpa, Integer> {

}
