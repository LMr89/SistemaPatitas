package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="raza")

public class RazaJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_raza")
	private Integer idRaza;
	
	@Column(name = "nombre")
	private String nombre;

	public RazaJpa(Integer idRaza) {
		this.idRaza = idRaza;
	}

	
}
