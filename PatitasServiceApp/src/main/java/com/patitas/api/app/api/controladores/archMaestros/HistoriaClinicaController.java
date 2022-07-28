package com.patitas.api.app.api.controladores.archMaestros;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patitas.api.app.dominio.entidades.archMaestro.HistoriaClinica;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.HistoriaClinicaPeticion;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.HistoriaClinicaAdaptador;

@RestController
@RequestMapping("/api/historia")
public class HistoriaClinicaController {
	@Autowired
	private HistoriaClinicaAdaptador historiaAdap;
	
	
	@PostMapping("/crear")
	@ResponseBody
	
	public ResponseEntity<String> crearHistoria(@RequestBody HistoriaClinicaPeticion his) {
		try {
			Boolean registrado = historiaAdap.crearNuevaHistoria(his);
			
			if (registrado) {
				return ResponseEntity.ok("Historia Registrada con exito");
			}else {
				return new ResponseEntity<>("La historia ya se encuentra registrada para la mascota", HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<HistoriaClinica> encontrarHistoriaPorIdMasctoa(@PathVariable Integer id) {
		try {
			Optional<HistoriaClinica> historiaEncontrada =  historiaAdap.encontrarHistoriaPorIdMascota(id);
			
			if (historiaEncontrada.isPresent()) {
				return ResponseEntity.ok(historiaEncontrada.get());
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.header("Error", e.getMessage())
					.body(null);
		}
		return null;
		
	}

	/*No habra un método put para Historia Clinica*/
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarHistoria(@PathVariable Integer id) {
		try {
		 historiaAdap.eliminarHistoria(id);
			return ResponseEntity.ok("Historia Eliminada con exito");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Hubo un error al eliminar la historia");
		}
		
	}
}
