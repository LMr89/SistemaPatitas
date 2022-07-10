package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;
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
	
	@JsonManagedReference(value = "veter-usuario")
	@OneToMany(mappedBy = "idUsu", cascade = CascadeType.ALL)
	private List<VeterinarioJpa> lstVeterinarios;

	
	@JsonManagedReference(value = "perfil-usuario")
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<PerfilUsuarioJpa> lstUsuario;
	
	@JsonManagedReference(value = "recep-usuario")
	@OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
	private List<RecepcionistaJpa> lstRecepcionista;
	
	public UsuarioJpa(Integer id) {
		this.id = id;
	}
	
	public UsuarioJpa() {
	}

	public UsuarioJpa(Integer id, String nombre, String apellidos, String dni, String direccion, String correo,
			String telefono, Boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.estado = estado;
	}

	
	

}
