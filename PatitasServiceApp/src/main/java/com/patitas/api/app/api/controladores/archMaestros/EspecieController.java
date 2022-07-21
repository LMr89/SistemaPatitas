package com.patitas.api.app.api.controladores.archMaestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.EspecieJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.EspecieRep;

@RestController
@RequestMapping("/api/especie")
public class EspecieController {
	@Autowired
	private EspecieRep especieRep;
	
	@GetMapping("/listar")
	public ResponseEntity< List<EspecieJpa>> listarEspecie() {
		
		return ResponseEntity.ok(especieRep.findAll());
	}

}
