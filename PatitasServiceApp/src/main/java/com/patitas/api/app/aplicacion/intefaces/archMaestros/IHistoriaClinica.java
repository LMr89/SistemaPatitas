package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.Optional;

import com.patitas.api.app.dominio.entidades.archMaestro.HistoriaClinica;

public interface IHistoriaClinica {
	HistoriaClinica crearNuevaHistoria(HistoriaClinica cli);
	Optional<HistoriaClinica> encontrarHistoriaPorIdMascota(Integer id);
	HistoriaClinica actualizarHistoria(HistoriaClinica cli);
	void eliminarHistoria(Integer id);

}
