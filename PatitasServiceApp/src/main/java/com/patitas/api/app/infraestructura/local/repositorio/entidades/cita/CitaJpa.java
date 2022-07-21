package com.patitas.api.app.infraestructura.local.repositorio.entidades.cita;

import java.io.Serializable;
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
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.ClienteJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.RecepcionistaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cita")
public class CitaJpa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita")
	private Integer idCita;
	
	@JsonBackReference(value = "cita-cliente")
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClienteJpa idCliente;
	
	
	@JsonBackReference(value = "cita-mascota")
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "idmascota")
	private MascotaJpa idMascota;
	
	@JsonBackReference(value = "vet_cita")
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "id_veterinario")
	private VeterinarioJpa idVeterinario;

	@JsonBackReference(value = "recep_cita")
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "idrecepcionista")
	private RecepcionistaJpa idRecepcionista;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Calendar fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_atencion")
	private Calendar fechaAtencion;
	
	@Column(name = "pendiente")
	private Boolean pendiente;
	
	@Column(name = "estado")
	private Boolean estado;

	public CitaJpa(Integer idCita, ClienteJpa idCliente, MascotaJpa idMascota, VeterinarioJpa idVeterinario,
			RecepcionistaJpa idRecepcionista, Calendar fechaRegistro, Calendar fechaAtencion, Boolean pendiente) {
		this.idCita = idCita;
		this.idCliente = idCliente;
		this.idMascota = idMascota;
		this.idVeterinario = idVeterinario;
		this.idRecepcionista = idRecepcionista;
		this.fechaRegistro = fechaRegistro;
		this.fechaAtencion = fechaAtencion;
		this.pendiente = pendiente;
	}
	
	
}
