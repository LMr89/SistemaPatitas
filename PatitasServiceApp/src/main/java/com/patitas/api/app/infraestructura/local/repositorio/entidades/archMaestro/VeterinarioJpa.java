package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.cita.CitaJpa;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@Entity
@Table(name = "Veterinario")
public class VeterinarioJpa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_veterinario")
	private Integer id;
	
	@JsonBackReference(value = "veter-usuario")
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_usuario")
	private UsuarioJpa idUsu;
	
	
	@Column(name="estado")
    private Boolean estado;

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



	public VeterinarioJpa(Integer id, UsuarioJpa idUsu, Boolean estado) {
		this.id = id;
		this.idUsu = idUsu;
		this.estado = estado;
	}

	public VeterinarioJpa(Integer id) {

		this.id = id;
	}




	@JsonManagedReference(value = "diag-vet")
	@OneToMany(mappedBy = "idVet", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<DiagnosticoJpa> lstDiagnosticos;
	
	
	@JsonManagedReference(value = "horario-vet")
	@OneToMany(mappedBy = "idVet", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<HorarioJpa> lstHorarios;
	
	@JsonManagedReference(value = "vet_cita")
	@OneToMany(mappedBy = "idVeterinario", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<CitaJpa> lsdCitas;

	/*
	@Override
	public String toString() {
		return "VeterinarioJpa{" +
				"id=" + id +
				", idUsu=" + idUsu +
				", estado=" + estado +
				", lstDiagnosticos=" + lstDiagnosticos +
				", lstHorarios=" + lstHorarios +
				", lsdCitas=" + lsdCitas +
				'}';
	}*/

}
