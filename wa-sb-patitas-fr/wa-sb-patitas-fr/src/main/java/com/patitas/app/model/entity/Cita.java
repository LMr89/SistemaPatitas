package com.patitas.app.model.entity;

import java.util.Calendar;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
	private Integer id;
	private Map<String, Object> cliente;
	private Map<String, Object> mascota;
	private Map<String, Object> veterinario;
	private Map<String, Object> recepcionista;
	private Calendar fechaRegistro;
    private Calendar fechaAtencion;
    private Boolean pendiente;
    
	@Override
	public String toString() {
		return "Cita [id=" + id + ", cliente=" + cliente + ", mascota=" + mascota
				+ ", veterinario=" + veterinario+ ", recepcionista=" + recepcionista
				+ ", fechaRegistro=" + fechaRegistro + ", fechaAtencion=" + fechaAtencion
				+", pendiente=" + pendiente+ "]";
	}
}
