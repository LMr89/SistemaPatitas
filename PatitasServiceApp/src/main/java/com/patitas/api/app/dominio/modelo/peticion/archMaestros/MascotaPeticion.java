package com.patitas.api.app.dominio.modelo.peticion.archMaestros;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaPeticion {
	private Integer id;
	private Integer idCliente;
	private String nombre;
	private Integer idRaza;
	private String color;
	private Integer idEspecie;
	

}
