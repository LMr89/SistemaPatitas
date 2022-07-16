package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.List;

import com.patitas.api.app.dominio.entidades.archMaestro.Mascota;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.MascotaPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.MascotaRespuesta;

public interface IMascota {
	default List<Mascota> listarMascotas(){return null;};
	MascotaRespuesta crearNuevaMascota(MascotaPeticion masco);
	List<MascotaRespuesta> encontrarMascotaPorIdCliente(Integer id);
	MascotaRespuesta actualizarMascota(MascotaPeticion masco);
	void eliminarMascota(Integer id);
}
