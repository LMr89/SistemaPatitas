package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "Historia_Clinica" )
public class HistoriaClinicaJpa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historia_clinica")
	private Integer id;
	
	@JsonBackReference(value = "historia-mascota")
	@ManyToOne
	@JoinColumn(name = "idmascota")
	private MascotaJpa idMascota;
	
	@Column(name = "estado")
	private Boolean Estado;
	
	@JsonManagedReference(value = "diag-historia")
	@OneToMany(mappedBy = "idHis", cascade = CascadeType.ALL)
	private List<DiagnosticoJpa> listaDiagnosticos;

	public HistoriaClinicaJpa() {
	}

	public HistoriaClinicaJpa(Integer id, MascotaJpa idMascota, Boolean estado) {
		this.id = id;
		this.idMascota = idMascota;
		Estado = estado;
	}
	
	
	

}
