package com.patitas.api.app.dominio.modelo.respuesta.archMaestros;

import java.util.List;

import com.patitas.api.app.dominio.entidades.archMaestro.Horario;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HorarioJpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarioRespuesta {
	
	private Integer id;
	private String nombre;
	private String apellidos;
	private List<HorarioJpa> horarios;
	
	
	public VeterinarioRespuesta(Integer id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	
	
	
	
	

}
