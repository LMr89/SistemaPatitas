package com.patitas.api.app.api.controladores.archMaestros;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HorarioJpa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patitas.api.app.dominio.modelo.peticion.archMaestros.HorarioPeticion;
import com.patitas.api.app.infraestructura.local.adaptadores.archMaestros.HorarioAdaptador;

@RestController
@RequestMapping("/api/horario")
public class HorarioController {
	private static Logger log = LogManager.getLogger(HorarioController.class);
	@Autowired
	private HorarioAdaptador adaptador;


	@GetMapping("/obtener/{Id}")
	public ResponseEntity<HorarioJpa> obtenerHorario(@PathVariable Integer Id){
		log.info(Id);
		ResponseEntity res  = null ;

		try {
			HorarioJpa encontrado  = adaptador.obtenerHorarioDeUsuarioVet(Id);
			if ( encontrado != null ) {
				res  =  ResponseEntity.ok(encontrado);

			}else {
				res  =  new ResponseEntity<>("Horario no encontrado",HttpStatus.NOT_FOUND);
			}

		}catch (Exception c){
			log.error("Error en obtener horario");
			res  =  new ResponseEntity<>(c.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res ;

	}




	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<String> registrarHorario(@RequestBody HorarioPeticion hPeticion){
		log.info(hPeticion.toString());
		ResponseEntity res  = null ;

		try {
			if (adaptador.registrarHorario(hPeticion)) {
				res  =  new ResponseEntity<>("Horario registrado exitosamente",HttpStatus.CREATED);

			}else {
				res  =  new ResponseEntity<>("No se pudo registrar la historia",HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}catch (Exception c){
			res  =  new ResponseEntity<>("Horario ya registrado para aquel veterinario",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res ;
		
	}

	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<String> actualizarHorario(@RequestBody HorarioPeticion hPeticion){
		log.info(hPeticion.toString());

		ResponseEntity res  = null ;

		try {
			if (adaptador.actualizarHorario(hPeticion)) {
				res  =  new ResponseEntity<>("Horario actualizado exitosamente",HttpStatus.CREATED);

			}else {
				res  =  new ResponseEntity<>("No se pudo actualizar la historia",HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}catch (Exception c){
			res  =  new ResponseEntity<>(c.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res ;

	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<String> eliminarHorario(@PathVariable Integer id){
		log.info(id);

		ResponseEntity res  = null ;

		try {
			if (adaptador.eliminarHorario(id)) {
				res  =  new ResponseEntity<>("Horario eliminado exitosamente",HttpStatus.CREATED);

			}else {
				res  =  new ResponseEntity<>("No se pudo eliminar la historia",HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}catch (Exception c){
			res  =  new ResponseEntity<>(c.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res ;

	}


}
