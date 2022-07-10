package com.patitas.api.app.dominio.modelo.respuesta.autenticacion;

import com.patitas.api.app.dominio.entidades.archMaestro.Accesos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRespuesta {
	private Boolean esAutenticado;
	private String username;
	private String Mensaje;
	Accesos accesos;
	
}
