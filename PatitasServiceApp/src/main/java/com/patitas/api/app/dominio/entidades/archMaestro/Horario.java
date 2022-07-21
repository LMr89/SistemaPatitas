package com.patitas.api.app.dominio.entidades.archMaestro;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
	
	private Integer idHora;
	private Veterinario idVet;
	private Date manInicio;
	private Date manFin;
	private Date tarInicio;
	private Date tarFin;

}
