package com.patitas.api.app.dominio.modelo.peticion.archMaestros;

import lombok.Data;

@Data
public class PerfilUsuarioPeticion {
		private String nombre;
		private String contraseña;
		private Integer idAcceso;
		private Integer idUsuario;

}
