package com.patitas.api.app.api.controladores.archMaestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.RazaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.RazaRep;

@RestController
@RequestMapping("/api/raza")
public class RazaController {
	@Autowired
	private RazaRep razaRep;
	
	@GetMapping("/listar")
	public ResponseEntity< List<RazaJpa>> listarRazas() {
		
		return ResponseEntity.ok(razaRep.findAll());
	}
	
	

}
