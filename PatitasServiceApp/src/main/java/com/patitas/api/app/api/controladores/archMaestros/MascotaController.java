package com.patitas.api.app.api.controladores.archMaestros;

import java.util.List;

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

import com.patitas.api.app.dominio.entidades.archMaestro.Mascota;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.MascotaPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.MascotaRespuesta;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.MascotaAdaptador;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
	@Autowired
	private MascotaAdaptador objMascotaAdap;
	
	@GetMapping("/listar")
	public ResponseEntity< List<Mascota>> listarMascotas() {
		
		return ResponseEntity.ok(objMascotaAdap.listarMascotas());
	}
	
	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<String> crearNuevaMascota(@RequestBody MascotaPeticion mascoPet) {
		try {
			objMascotaAdap.crearNuevaMascota(mascoPet
					);
			
			return ResponseEntity.ok("Mascota registrada con exito");
			
		}catch(Exception e ) {
			return ResponseEntity.badRequest()
					.body(e.getMessage())
					;
		} 
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<List<MascotaRespuesta>> encontrarMascotaPorIdCliente(@PathVariable Integer id) {
		try {
			List<MascotaRespuesta>mascotaEncontrada =  objMascotaAdap.encontrarMascotaPorIdCliente(id);
			if (mascotaEncontrada.size() > 0) {
				return ResponseEntity.ok(mascotaEncontrada);
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
	public ResponseEntity<String> actualizarMascota(@RequestBody MascotaPeticion mascoPet) {
		try {
			 objMascotaAdap.actualizarMascota(mascoPet);
			return ResponseEntity.ok("Mascota actualizada con exito");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Mascota no actualizada");
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public  ResponseEntity<String>  eliminarMascota(@PathVariable Integer id) {
		try {
		 objMascotaAdap.eliminarMascota(id);
			return ResponseEntity.ok("Mascota eliminada exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Mascota no eliminada");
		}
		
	}

}
