package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioJpa  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@ToString.Exclude
	private List<VeterinarioJpa> lstVeterinarios;

	
	@JsonManagedReference(value = "perfil-usuario")
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<PerfilUsuarioJpa> lstUsuario;
	
	@JsonManagedReference(value = "recep-usuario")
	@OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<RecepcionistaJpa> lstRecepcionista;


	@JsonManagedReference(value = "administrador_usuario")
	@OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<AdministradorJpa> lstAdministrador;

	public UsuarioJpa(Integer id) {
		this.id = id;
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
