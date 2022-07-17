package com.patitas.app.model.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {
	private Integer id;
	private String nombre;
	private String apellidos;
	private List<Horario> horarios;
	
}
