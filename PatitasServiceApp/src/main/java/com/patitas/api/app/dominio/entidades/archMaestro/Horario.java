package com.patitas.api.app.dominio.entidades.archMaestro;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
	public Horario(Integer id, Veterinario mapDeEntidadJpaADominio, java.util.Date fechaInicio,
			java.util.Date fechaFin) {
		// TODO Auto-generated constructor stub
	}
	private Integer idHora;
	private Veterinario idVet;
	private Date horaInicio;
	private Date horaFin;

}
