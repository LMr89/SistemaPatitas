package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.ICliente;
import com.patitas.api.app.dominio.entidades.archMaestro.Cliente;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.ClienteJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.ClienteRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.ClienteMaper;

@Service
public class ClienteAdaptador implements ICliente{
	/*Se usa la inyeccion de depencias para el repositorio */
	@Autowired
	private ClienteRep clienteRep;

	@Override
	public List<Cliente> listarClientes() {
		/*Como el repositorio lista clases de la entidad Jpa
		 * LO QUE SE HACE ES QUE POR CADA CLASE JPA SE LE PASE COMO 
		 * ARGUMENTO A LA FUNCION: mapDeEntidadJpaADominio PARA 
		 * CONVERTIRLAS A CLASE DEL DOMINIO  */
		return clienteRep.findAll()
				.stream()
				.map(ClienteMaper::mapDeEntidadJpaADominio)
				.collect(Collectors.toList())
				;
	}

	@Override
	public Cliente crearNuevoCLiente(Cliente cli) {
		/*De igual manera para guardar un registro se tiene que convertir
		 *  de la clase CLIENTE DOMINIO  a la clase CLIENTE JPA*/
		ClienteJpa clienCreado = clienteRep.save(ClienteMaper.mapDeDominioAEntidadJpa(cli));
		return  ClienteMaper.mapDeEntidadJpaADominio(clienCreado);
	}

	@Override
	public Optional<Cliente> encontrarCLientePorDni(String dni) {
		/*La clase JPA encontrada por el id se convierte en clase 
		 * del dominio y esta se envuelve en una clase OPTIONAL y se retorna*/
		return Optional.of(ClienteMaper.mapDeEntidadJpaADominio(
				clienteRep.findByDni(dni).get()));
	}
	
	

	@Override
	public Optional<Cliente> encontrarCLientePorId(Integer id) {
		// TODO Auto-generated method stub
		return Optional.of(ClienteMaper.mapDeEntidadJpaADominio(
				clienteRep.findById(id).get()));
	}

	@Override
	public Cliente actualizarCliente(Cliente cli) {
		/*Para actualzar un registro se tiene que convertir
		 *  de la clase CLIENTE DOMINIO  a la clase CLIENTE JPA*/
		ClienteJpa clienActualizado = clienteRep.save(ClienteMaper.mapDeDominioAEntidadJpa(cli));
		return  ClienteMaper.mapDeEntidadJpaADominio(clienActualizado);
	}

	@Override
	public void eliminarCLiente(Integer cli) {
		/*Para eliminar un registro se tiene que convertir
		 *  de la clase CLIENTE DOMINIO  a la clase CLIENTE JPA*/
		
		clienteRep.delete(ClienteMaper.mapDeDominioAEntidadJpa(
				encontrarCLientePorId(cli).get()));
		
	}
	

}
