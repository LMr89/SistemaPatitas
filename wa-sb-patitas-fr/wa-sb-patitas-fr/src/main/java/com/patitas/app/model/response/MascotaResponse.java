package com.patitas.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaResponse {
	private Integer idMascota;
	private String raza;
	private String color;
	private String especie;
	private String nombre;
	
	

}
