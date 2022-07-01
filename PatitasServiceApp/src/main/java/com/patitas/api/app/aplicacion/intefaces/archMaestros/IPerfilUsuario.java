package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.Optional;

import com.patitas.api.app.dominio.entidades.PerfilUsuario;
import com.patitas.api.app.dominio.entidades.Usuario;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.PerfilUsuarioPeticion;

public interface IPerfilUsuario {
	//List<PerfilUsuario> listarPerfiles();
	PerfilUsuario crearNuevoPerfil(PerfilUsuarioPeticion perf);
	Optional<PerfilUsuario> encontrarPerfilPorIdUSuario(Usuario id);
	PerfilUsuario actualizarPerfil(PerfilUsuarioPeticion perf);
	void eliminarPerfil(Integer id);
	

}
