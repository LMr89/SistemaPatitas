package com.patitas.api.app.dominio.entidades.archMaestro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
	private Integer id;
	private Cliente idCliente;
	private String nombre;
	private Integer raza;
	private String color;
	private Integer especie;
	private Boolean estado;
	

}
