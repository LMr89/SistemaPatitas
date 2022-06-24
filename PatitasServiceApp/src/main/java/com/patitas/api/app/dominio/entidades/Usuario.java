package com.patitas.api.app.dominio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	private PerfilUsuario idPerfilUsuario;
	private String nombre;
	private String apellidos;
	private String dni;
	private String Direccion;
	private String email;
	private String telefono;
	private Boolean estado;

}
