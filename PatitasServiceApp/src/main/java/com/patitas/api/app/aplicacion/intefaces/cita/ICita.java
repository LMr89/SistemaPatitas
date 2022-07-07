package com.patitas.api.app.aplicacion.intefaces.cita;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.Cita;

public interface ICita {
	List<Cita> listarCitas();
	Cita crearNuevaCita(Cita cita);
	Optional<Cita> encontrarCitaPorId(Integer id);
	Cita actualizarCita(Cita cita);
	void eliminarCita(Integer id);
}
