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
public class Cita {
	private Integer id;
	private Mascota idMascota;
	private Veterinario idVeterinario;
	private Recepcionista idRecepcionista;
	private Date fechaRegistro;
	private Date fechaAtencion;
	private Boolean pendiente;
	

}
