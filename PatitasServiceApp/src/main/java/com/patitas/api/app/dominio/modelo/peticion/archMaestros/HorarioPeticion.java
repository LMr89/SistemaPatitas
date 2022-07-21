package com.patitas.api.app.dominio.modelo.peticion.archMaestros;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioPeticion {
	private Integer idVeterinario;
	private Date manInicio;
	private Date manFin;
	private Date tarInicio;
	private Date tarFin;

	
}
