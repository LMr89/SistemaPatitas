package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.Optional;

import com.patitas.api.app.dominio.entidades.archMaestro.HistoriaClinica;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.HistoriaClinicaPeticion;

public interface IHistoriaClinica {
	Boolean crearNuevaHistoria(HistoriaClinicaPeticion cli);
	Optional<HistoriaClinica> encontrarHistoriaPorIdMascota(Integer id);
	Boolean actualizarHistoria(HistoriaClinicaPeticion cli);
	Boolean eliminarHistoria(Integer id);

}
