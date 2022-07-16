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
	private String raza;
	private String color;
	private String especie;
	


	public MascotaRespuesta(Integer idMascota, String nombre) {
		this.idMascota = idMascota;
		Nombre = nombre;
	}



	public MascotaRespuesta(String nombre2) {
		// TODO Auto-generated constructor stub
	}
	
	
}
