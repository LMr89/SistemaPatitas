package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import java.util.Calendar;


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

@Data
@AllArgsConstructor
@Entity
@Table(name = "Diagnostico")
public class DiagnosticoJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDiagnostico")
	private Integer id;
	
	@JsonBackReference(value = "diag-vet")
	//cascade = CascadeType.PERSIST
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "idveterinario")
	private VeterinarioJpa idVet;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Calendar fecha;
	
	@Column(name = "detalles")
	private String detalles;
	
	@JsonBackReference(value = "diag-historia")
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "id_historia_clinica")
	private HistoriaClinicaJpa idHis;

	public DiagnosticoJpa() {
	}
	
	

}
