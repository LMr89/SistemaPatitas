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
@Table(name="especie")
public class EspecieJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_especie")
	private Integer idEspecie;
	
	@Column(name = "nombre")
	private String nombre;

	public EspecieJpa(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}
	
	
	
	
}
