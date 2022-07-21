package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

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
import com.patitas.api.app.infraestructura.local.repositorio.entidades.cita.CitaJpa;

import lombok.Data;


@Data
@Entity
@Table(name = "mascota")
public class MascotaJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmascota")
	private Integer id;
	
	@JsonBackReference(value = "mascota-cliente")
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClienteJpa idCliente;
	
	@Column(name = "nombre")
	private String nombre;
	
	@JsonBackReference(value = "mascota-raza")
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "id_raza")
	private RazaJpa raza;
	
	
	@Column(name = "color")
	private String color;
	
	@JsonBackReference(value = "mascota-especie")
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especie")
	private EspecieJpa especie;
	
	@Column(name = "estado")
	private Boolean estado;
	
	public MascotaJpa(Integer id) {
		this.id = id;
	}

	
	public MascotaJpa() {
	}
	
	public MascotaJpa(Integer id, ClienteJpa idCli, String nombre, RazaJpa raza, String color, EspecieJpa especie,
			Boolean estado) {
		this.id = id;
		this.idCliente = idCli;
		this.nombre = nombre;
		this.raza = raza;
		this.color = color;
		this.especie = especie;
		this.estado = estado;
	}

	@JsonManagedReference(value = "historia-mascota")
	@OneToMany(mappedBy = "idMascota",  cascade = CascadeType.ALL)
	public List<HistoriaClinicaJpa> lstMascotas;
	
	@JsonManagedReference(value = "cita-mascota")
	@OneToMany(mappedBy = "idMascota",  cascade = CascadeType.ALL)
	public List<CitaJpa> lstCitas;
	
	

}