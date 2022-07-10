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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recepcionista")
public class RecepcionistaJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idrecepcionista")
	private Integer idRecepcionista;
	
	@JsonBackReference("recep-usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioJpa idUsuario;

}
