package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HistoriaClinicaJpa;

@Repository
public interface HistoriaClinicaRep extends JpaRepository<HistoriaClinicaJpa, Integer> {

}
