package com.patitas.api.app.infraestructura.local.repositorio.entidades;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Entity
@Table(name="accesos")
public class AccesosJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_acceso")
	private Integer id;
	@Column(name = "gestion_citas")
	private Boolean gestionCitas;
	@Column(name = "gestion_atencion")
	private Boolean gestionAtencion;
	@Column(name = "gestion_admision")
	private Boolean gestionAdmision;
	@Column(name = "gestion_seguridad")
	private Boolean gestionSeguridad;
	
	
	@JsonManagedReference(value = "perfil-acceso")
	@OneToMany(mappedBy = "accesos", cascade = CascadeType.ALL)
	public List<PerfilUsuarioJpa> perfilesUsuarios;
	 
	
	public AccesosJpa(Integer id, Boolean gestionCitas, Boolean gestionAtencion, Boolean gestionAdmision,
			Boolean gestionSeguridad) {
		this.id = id;
		this.gestionCitas = gestionCitas;
		this.gestionAtencion = gestionAtencion;
		this.gestionAdmision = gestionAdmision;
		this.gestionSeguridad = gestionSeguridad;
	}
	
	
	
	
	

}
