package com.patitas.api.app.aplicacion.intefaces.cita;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.cita.Cita;
import com.patitas.api.app.dominio.modelo.peticion.cita.CitaPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.cita.CitaRespuesta;

public interface ICita {
	List<Cita> listarCitas();
	CitaRespuesta crearNuevaCita(CitaPeticion cita);
	Optional<Cita> encontrarCitaPorId(Integer id);
	CitaRespuesta actualizarCita(CitaPeticion cita);
	void eliminarCita(Integer id);
}
