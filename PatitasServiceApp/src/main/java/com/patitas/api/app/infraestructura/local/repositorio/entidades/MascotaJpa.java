package com.patitas.api.app.infraestructura.local.repositorio.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Data
@Entity
@Table(name = "Mascota")
public class MascotaJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmascota")
	private Integer id;
	
	@JsonBackReference(value = "mascota-cliente")
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClienteJpa idCli;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "raza")
	private String raza;
	@Column(name = "color")
	private String color;
	@Column(name = "especie")
	private String especie;
	@Column(name = "estado")
	private Boolean estado;
	
	public MascotaJpa(Integer id) {
		this.id = id;
	}

	public MascotaJpa() {
	}
	
	@JsonManagedReference(value = "historia-mascota")
	@OneToMany(mappedBy = "idMascota",  cascade = CascadeType.ALL)
	public List<HistoriaClinicaJpa> lstMascotas;
	

}