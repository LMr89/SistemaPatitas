package com.patitas.app.model.entity;

import java.io.Serializable;

public class Mascota implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idCliente;
	private String nombre;
	private String idRaza;
	private String color;
	private Integer idEspecie;
	private Boolean estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdRaza() {
		return idRaza;
	}

	public void setIdRaza(String idRaza) {
		this.idRaza = idRaza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Mascota(Integer id, Integer idCliente, String nombre, String idRaza, String color, Integer idEspecie,
			Boolean estado) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.idRaza = idRaza;
		this.color = color;
		this.idEspecie = idEspecie;
		this.estado = estado;
	}

	public Mascota() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	
	public String toString() {
		// TODO Auto-generated method stub
		return id +" "+idCliente+" " + nombre +" "+ idRaza +" "+ color +" "+ idEspecie +" "+ estado +" ";
	}
}
