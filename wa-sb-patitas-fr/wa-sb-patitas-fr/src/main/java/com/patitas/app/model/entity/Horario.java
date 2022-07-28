package com.patitas.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
	private Integer id;
	private String manInicio;
	private String manFin;
	private String tarInicio;
	private String tarFin;

}
