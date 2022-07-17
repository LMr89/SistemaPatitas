package com.patitas.app.model.entity;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
	private Integer id;
	private String fechaInicio;
	private String fechaFin;

}
