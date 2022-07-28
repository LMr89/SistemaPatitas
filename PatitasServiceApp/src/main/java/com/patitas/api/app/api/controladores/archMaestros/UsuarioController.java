package com.patitas.api.app.api.controladores.archMaestros;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.UsuarioAdaptador;

@RestController
@RequestMapping("/api/usuario")

public class UsuarioController {
	private static Logger log  = LogManager.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioAdaptador usuarioAdapter;
	
	@GetMapping("/listar")
	public ResponseEntity< List<Usuario>> listarUsuario() {

		
		return ResponseEntity.ok(usuarioAdapter.listarUsuarios());
	}
	
	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<String> crearNuevoUsuario(@RequestBody Usuario usuario) {
		try {
			 usuarioAdapter.crearNuevoUsuario(usuario);
			
			return ResponseEntity.ok("Usuario registrado con exito");
			
		}catch(DataIntegrityViolationException cons) {
			return ResponseEntity.badRequest()
					.body("Dni ya existe")
					;
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Usuario> encontrarUsuarioPorId(@PathVariable Integer id) {
		try {
			Optional<Usuario>UsuarioEncontrado =  usuarioAdapter.encontrarUsuarioPorId(id);
			if (UsuarioEncontrado.isPresent()) {
				return ResponseEntity.ok(UsuarioEncontrado.get());
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body(new Usuario());
		}
		return null;
		
		
	}

	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usu) {
		try {
			usuarioAdapter.actualizarUsuario(usu);
			return ResponseEntity.ok("Usuario actualizado");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Usuario no actualizado");
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarUsuario(@PathVariable Integer id) {
		try {
		 usuarioAdapter.eliminarUsuario(id);
			return ResponseEntity.ok("Usuario eliminado exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body("Usuario no eliminado");
		}
		
	}
}
