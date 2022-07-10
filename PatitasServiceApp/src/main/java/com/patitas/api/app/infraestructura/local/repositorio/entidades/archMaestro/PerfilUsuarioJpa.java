package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfil_usuario")
public class PerfilUsuarioJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idperfilusuario")
	private Integer id;
	@Column(name = "nombreusuario")
	private String nombreUsuario;
	@Column(name = "contraseñausuario")
	private String contraseniaUsuario;
	
	
	@JsonBackReference(value = "perfil-acceso")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_acceso")
	private AccesosJpa accesos;
	
	
	
	@JsonBackReference(value = "perfil-usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioJpa usuario;

	

	public PerfilUsuarioJpa(Integer id, String nombreUsuario, String contraseniaUsuario, AccesosJpa accesos) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
		this.accesos = accesos;
	}

}
