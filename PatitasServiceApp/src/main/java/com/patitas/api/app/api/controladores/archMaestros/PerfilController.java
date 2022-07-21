package com.patitas.api.app.api.controladores.archMaestros;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.patitas.api.app.dominio.entidades.archMaestro.PerfilUsuario;
import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.PerfilUsuarioPeticion;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.PerfilUsuarioAdaptador;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {
	@Autowired
	private PerfilUsuarioAdaptador perfilAdap;
	
	/*
	@GetMapping("/listar")
	public ResponseEntity< List<Cliente>> listarClientes() {
		
		return ResponseEntity.ok(perfilAdap.listarClientes());
	}
	*/
	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<String> crearNuevoCLiente(@RequestBody PerfilUsuarioPeticion perf) {
		try {
			perfilAdap.crearNuevoPerfil(perf);
			
			return ResponseEntity.ok("Perfil registrado con exito");
			
		}catch(Exception e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage())
					;
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<PerfilUsuario> encontrarCLientePorId(@PathVariable Integer id) {
		try {
			Optional<PerfilUsuario>perfilEncontrado = 
					perfilAdap.encontrarPerfilPorIdUSuario(new Usuario(id));
			if (perfilEncontrado.isPresent()) {
				return ResponseEntity.ok(perfilEncontrado.get());
			}else{
				return ResponseEntity.ok(new PerfilUsuario(0,"No encontrado",
						"No encontrado",null,null));
			}


		} catch (Exception e) {
			System.out.println("Perfil no encontrado");
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body(new PerfilUsuario(0,"No encontrado",
							"No encontrado",null,null));
		}
	
		
		
	}

	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<String> actualizarPerfil(@RequestBody PerfilUsuarioPeticion perf) {
		try {
			perfilAdap.actualizarPerfil(perf);
			return ResponseEntity.ok("Perfil actualizado");
			
		} catch (Exception e) {
		
			return ResponseEntity.badRequest().body("Perfil no actualizado" + e.getMessage());
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarPerfil(@PathVariable Integer id) {
		try {
		 perfilAdap.eliminarPerfil(id);
			return ResponseEntity.ok("Perfil eliminado exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Perfil no eliminado");
		}
		
	}
}
