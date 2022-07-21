package com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.EspecieJpa;

@Repository
public interface EspecieRep extends JpaRepository<EspecieJpa, Integer> {

}
