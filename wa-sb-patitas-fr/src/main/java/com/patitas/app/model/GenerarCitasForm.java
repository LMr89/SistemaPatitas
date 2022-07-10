package com.patitas.app.model;

import java.io.Serializable;

public class GenerarCitasForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreVeterinario;
	private String nombreMascota;
	private String nomCliente;
	public String getNombreVeterinario() {
		return nombreVeterinario;
	}
	public void setNombreVeterinario(String nombreVeterinario) {
		this.nombreVeterinario = nombreVeterinario;
	}
	public String getNombreMascota() {
		return nombreMascota;
	}
	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	public GenerarCitasForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GenerarCitasForm(String nombreVeterinario, String nombreMascota, String nomCliente) {
		super();
		this.nombreVeterinario = nombreVeterinario;
		this.nombreMascota = nombreMascota;
		this.nomCliente = nomCliente;
	}
	

}
