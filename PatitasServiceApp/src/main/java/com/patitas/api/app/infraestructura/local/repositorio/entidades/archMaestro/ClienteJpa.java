package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.cita.CitaJpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteJpa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idcliente;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "dni")
	private String dni;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "correo")
	private String correo;
	@Column(name = "telefono")
	private String telefono;
	@Column(name = "estado")
	private Boolean estado;
	
	
	
	
	public ClienteJpa(Integer idcliente, String nombre, String apellidos, String dni, String direccion, String correo,
			String telefono, Boolean estado) {
		this.idcliente = idcliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.estado = estado;
	}
	

	public ClienteJpa(Integer idcliente) {
		this.idcliente = idcliente;
	}


	
	@JsonManagedReference(value = "cita-cliente")
	@OneToMany(mappedBy = "idCliente",  cascade = CascadeType.ALL )
	public List<CitaJpa> lstCita;
	 


	@JsonManagedReference(value = "mascota-cliente")
	@OneToMany(mappedBy = "idCliente",  cascade = CascadeType.ALL )
	public List<MascotaJpa> lstMascotas;

}
