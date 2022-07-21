package com.patitas.api.app.api.controladores.archMaestros;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.VeterinarioAdaptador;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.VeterinarioRep;

@RestController
@RequestMapping("/api/veterinario")
public class VeterinarioController {
	@Autowired
	private VeterinarioAdaptador objVetAdaptador;
	
	@Autowired
	private VeterinarioRep vetRep;
	
	@GetMapping("/listar")
	public ResponseEntity< List<VeterinarioRespuesta>> listarVeterinarios() {
		
		return ResponseEntity.ok(objVetAdaptador.listarVeterinario());
	}
	
	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<String> crearNuevoVet(@RequestBody Veterinario vet) {
		try {
			objVetAdaptador.crearNuevoVeterinario(vet);
			return ResponseEntity.ok("Veterinario registrada con exito");
			
		}catch(Exception e ) {
			return ResponseEntity.badRequest()
					.body(e.getMessage())
					;
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Integer> encontrarVeterinarioPorIdCliente(@PathVariable Integer id) {
		try {
			Optional<VeterinarioJpa>veterinarioEncontrado = vetRep.findByIdUsu(new UsuarioJpa(id));
			
			if (veterinarioEncontrado.isPresent()) {
				return ResponseEntity.ok(veterinarioEncontrado.get().getId());
			}else {
				return new ResponseEntity<Integer>(0,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body(null);
		}
	
		
	}

	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<String> actualizarVeterinario(@RequestBody Veterinario vet) {
		try {
			 objVetAdaptador.actualizarVeterinario(vet);
			return ResponseEntity.ok("Veterinario actualizado con exito");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Veterinario no actualizada");
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarVet(@PathVariable Integer id) {
		try {
		 objVetAdaptador.eliminarVeterinario(id);
			return ResponseEntity.ok("Veterinario eliminado exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Veterinario no eliminado");
		}
		
	}

}
