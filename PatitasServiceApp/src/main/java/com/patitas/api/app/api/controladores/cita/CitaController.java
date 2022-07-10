package com.patitas.api.app.api.controladores.cita;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.patitas.api.app.dominio.entidades.archMaestro.Cliente;
import com.patitas.api.app.dominio.entidades.cita.Cita;
import com.patitas.api.app.dominio.modelo.peticion.cita.CitaPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.cita.CitaRespuesta;
import com.patitas.api.app.infraestructura.local.adaptadores.cita.CitaAdaptador;
import com.patitas.api.app.infraestructura.mapeadores.cita.CitaMapeador;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/cita")
public class CitaController {
	@Autowired
	private CitaAdaptador citaAdap;
	
	@GetMapping("/listar")
	public ResponseEntity< List<CitaRespuesta>> listarCitas() {
		
		return ResponseEntity.ok(citaAdap.listarCitas().stream().map(CitaMapeador::mapDeDominioACitaRespuesta)
				.collect(Collectors.toList()));
	}
	
	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<String> crearNuevaCita(@RequestBody CitaPeticion objCita) {
		try {
			citaAdap.crearNuevaCita(objCita);
			
			return ResponseEntity.ok("Cita generada con exito");
			
		}catch(Exception e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage())
					;
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<CitaRespuesta> encontrarCitaPorId(@PathVariable Integer id) {
		try {
			Optional<Cita>citaEncontrada =  citaAdap.encontrarCitaPorId(id);
			if (citaEncontrada.isPresent()) {
				return ResponseEntity.ok(CitaMapeador.mapDeDominioACitaRespuesta(citaEncontrada.get()));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body(null);
		}
		return null;
		
	}

	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<String> actualizarCita(@RequestBody CitaPeticion objCita) {
		try {
			 citaAdap.actualizarCita(objCita);
			return ResponseEntity.ok("Cita actualizada");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Cita no actualizada-> " + e.getMessage());
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarCita(@PathVariable Integer id) {
		try {
		 citaAdap.eliminarCita(id);
			return ResponseEntity.ok("Cita eliminada exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Cita no eliminada - " + e.getMessage());
		}
		
	}
	
	

}
