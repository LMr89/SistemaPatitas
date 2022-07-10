package com.patitas.api.app.dominio.entidades.cita;

import java.util.Calendar;

import com.patitas.api.app.dominio.entidades.archMaestro.Cliente;
import com.patitas.api.app.dominio.entidades.archMaestro.Mascota;
import com.patitas.api.app.dominio.entidades.archMaestro.Recepcionista;
import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;

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
	private Cliente idCliente;
	private Mascota idMascota;
	private Veterinario idVeterinario;
	private Recepcionista idRecepcionista;
	private Calendar fechaRegistro;
	private Calendar fechaAtencion;
	private Boolean pendiente;
	

}
