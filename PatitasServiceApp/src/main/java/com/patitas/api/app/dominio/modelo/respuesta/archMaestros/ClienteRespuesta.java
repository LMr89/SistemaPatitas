package com.patitas.api.app.dominio.modelo.respuesta.archMaestros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRespuesta {
	private Integer id;
	private String nombre;
	private String apellidos;
	private String dni;
	
	

}
