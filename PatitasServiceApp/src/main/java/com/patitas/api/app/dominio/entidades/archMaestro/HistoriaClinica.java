package com.patitas.api.app.dominio.entidades.archMaestro;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaClinica {
	private Integer id;
	private Mascota idMascota;
	private Calendar fechaCreacion;
	private Boolean estado;

}
