package com.patitas.api.app.dominio.modelo.peticion.cita;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaPeticion {
	private Integer idCita;
	private Integer idCliente;
	private Integer idMascota;
	private Integer idVeterinario;
	private Integer idRecepcionista;
	private Calendar fechaRegistro;
	private Calendar fechaAtencion;
	private Boolean pendiente;
	private Boolean estado;


}
