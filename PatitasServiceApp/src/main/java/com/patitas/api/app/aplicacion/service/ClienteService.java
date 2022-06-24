package com.patitas.api.app.aplicacion.service;

import java.util.List;
import java.util.Optional;

import com.patitas.api.app.dominio.entidades.Cliente;

public interface  ClienteService {
	List<Cliente> listarClientes();
	Cliente crearNuevoCLiente(Cliente cli);
	Optional<Cliente> encontrarCLientePorId(Integer id);
	Cliente actualizarCliente(Cliente cli);
	void eliminarCLiente(Cliente cli);
}
