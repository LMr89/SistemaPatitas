package com.patitas.api.app.aplicacion.intefaces.archMaestros;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.archMaestro.Cliente;

public interface  ICliente {
	List<Cliente> listarClientes();
	Cliente crearNuevoCLiente(Cliente cli);
	Optional<Cliente> encontrarCLientePorId(Integer id);
	Cliente actualizarCliente(Cliente cli);
	void eliminarCLiente(Integer id);
}
