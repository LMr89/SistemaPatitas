package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HistoriaClinicaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;

@Repository
public interface HistoriaClinicaRep extends JpaRepository<HistoriaClinicaJpa, Integer> {
	Optional<HistoriaClinicaJpa> findByIdMascota(MascotaJpa idMascota);

}
