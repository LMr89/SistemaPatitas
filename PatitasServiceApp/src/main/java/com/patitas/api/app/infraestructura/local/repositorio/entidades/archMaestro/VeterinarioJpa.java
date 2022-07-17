package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

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
@AllArgsConstructor
@Entity
@Table(name = "Veterinario")
public class VeterinarioJpa {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_veterinario")
	private Integer id;
	
	@JsonBackReference(value = "veter-usuario")
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_usuario")
	private UsuarioJpa idUsu;

	public VeterinarioJpa() {
	}
	
	
	public VeterinarioJpa(Integer id, UsuarioJpa idUsu) {
		this.id = id;
		this.idUsu = idUsu;
	}
	
	

	public VeterinarioJpa(Integer id, List<DiagnosticoJpa> lstDiagnosticos, List<HorarioJpa> lstHorarios) {
		this.id = id;
		this.lstDiagnosticos = lstDiagnosticos;
		this.lstHorarios = lstHorarios;
	}



	@JsonManagedReference(value = "diag-vet")
	@OneToMany(mappedBy = "idVet", cascade = CascadeType.ALL)
	private List<DiagnosticoJpa> lstDiagnosticos;
	
	
	@JsonManagedReference(value = "horario-vet")
	@OneToMany(mappedBy = "idVet", cascade = CascadeType.ALL)
	private List<HorarioJpa> lstHorarios;

	
	
	

}
