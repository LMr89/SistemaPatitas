package com.patitas.api.app.dominio.entidades;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {
	private Integer id;
	private Veterinario idVeterinario;
	private HistoriaClinica idHistoriaClin;
	private Date fecha;
	private String detalles;
	

}
