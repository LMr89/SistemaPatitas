package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horario")
public class HorarioJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario")
	private Integer id;
	
	@JsonBackReference(value = "horario-vet")
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "id_veterinario")
	private VeterinarioJpa idVet;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio")
	private Date fechaInicio;
	
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fin")
	private Date fechaFin;

}
