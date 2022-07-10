package com.patitas.api.app.dominio.modelo.respuesta.archMaestros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaRespuesta {
	
	private Integer idMascota;
	private String Nombre;
}
