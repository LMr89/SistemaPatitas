package com.patitas.api.app.api.controladores.archMaestros;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patitas.api.app.dominio.entidades.Cliente;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.ClienteRespuesta;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.ClienteAdaptador;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController  {
	@Autowired
	private ClienteAdaptador clienteAdap;
	
	@GetMapping("/listar")
	public ResponseEntity< List<Cliente>> listarClientes() {
		
		return ResponseEntity.ok(clienteAdap.listarClientes());
	}
	
	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<String> crearNuevoCLiente(@RequestBody Cliente cli) {
		try {
			Cliente nuevoCliente = clienteAdap.crearNuevoCLiente(cli);
			
			return ResponseEntity.ok("Cliente registrado con exito");
			
		}catch(DataIntegrityViolationException cons) {
			return ResponseEntity.badRequest()
					.body("Dni ya existe")
					;
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Cliente> encontrarCLientePorId(@PathVariable Integer id) {
		try {
			Optional<Cliente>cliEncontrado =  clienteAdap.encontrarCLientePorId(id);
			if (cliEncontrado.isPresent()) {
				return ResponseEntity.ok(cliEncontrado.get());
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body(new Cliente());
		}
		return null;
		
		
	}

	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cli) {
		try {
			Cliente clienteActua = clienteAdap.actualizarCliente(cli);
			return ResponseEntity.ok("Cliente actualizado");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Cliente no actualizado");
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarCLiente(@PathVariable Integer id) {
		try {
		 clienteAdap.eliminarCLiente(id);
			return ResponseEntity.ok("Cliente eliminado exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Cliente no eliminado");
		}
		
	}
	
	

}
