package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;

public interface IUsuario {
	List<Usuario> listarUsuarios();
	Usuario crearNuevoUsuario(Usuario cli);
	Optional<Usuario> encontrarUsuarioPorId(Integer id);
	Usuario actualizarUsuario(Usuario cli);
	void eliminarUsuario(Integer id);
}
