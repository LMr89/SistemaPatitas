package com.patitas.api.app.api.controladores.autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patitas.api.app.dominio.modelo.peticion.autenticacion.LoginPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.autenticacion.LoginRespuesta;
import com.patitas.api.app.infraestructura.local.adaptadores.autenticacion.LoginAdapter;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	private LoginAdapter logAdapter;
	
	@CrossOrigin("http://localhost:8040")
	@PostMapping("/auth")
	@ResponseBody
	public ResponseEntity<LoginRespuesta> iniciarSesion(@RequestBody LoginPeticion pet) {
		LoginRespuesta resp = null;
		try {
			resp = logAdapter.iniciarSesion(pet);
			return ResponseEntity.ok(resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
	}

}
