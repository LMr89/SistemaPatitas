package com.patitas.api.app.dominio.modelo.peticion.archMaestros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioPeticion {
		private Integer idPerfil;
		private String nombre;
		private String contraseña;
		private Integer idAcceso;
		private Integer idUsuario;

}
