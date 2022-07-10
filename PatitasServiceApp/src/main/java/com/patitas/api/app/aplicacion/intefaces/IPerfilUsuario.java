package com.patitas.api.app.aplicacion.intefaces;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.archMaestro.Cliente;
import com.patitas.api.app.dominio.entidades.archMaestro.PerfilUsuario;

public interface IPerfilUsuario {
	List<Cliente> listarPerfiles();
	PerfilUsuario crearNuevoPerfil(PerfilUsuario cli);
	Optional<PerfilUsuario> encontrarPerfilPorId(Integer id);
	PerfilUsuario actualizarPerfil(PerfilUsuario cli);
	void eliminarPerfil(PerfilUsuario cli);
}
