package com.patitas.api.app.dominio.modelo.respuesta.cita;

import java.util.Calendar;

import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.ClienteRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.MascotaRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.RecepcionistaRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaRespuesta {
	private Integer id;
	private ClienteRespuesta cliente;
	private MascotaRespuesta mascota;
	private VeterinarioRespuesta veterinario;
	private RecepcionistaRespuesta recepcionista;
	private Calendar fechaRegistro;
	private Calendar fechaAtencion;
	private Boolean pendiente;
	


}
