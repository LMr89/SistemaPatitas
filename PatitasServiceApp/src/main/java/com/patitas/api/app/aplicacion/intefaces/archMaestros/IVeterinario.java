package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;

public interface IVeterinario {
	List<VeterinarioRespuesta> listarVeterinario();
	VeterinarioRespuesta crearNuevoVeterinario(Veterinario vet);
	Optional<Veterinario> encontrarVeterinarioPorIdCliente(Integer id);
	Veterinario actualizarVeterinario(Veterinario vet);
	void eliminarVeterinario(Integer id);
}
