package com.patitas.api.app.dominio.modelo.peticion.autenticacion;

import lombok.Data;

@Data
public class LoginPeticion {
	private String nomUsuario;
	private String contrasenia;

}
