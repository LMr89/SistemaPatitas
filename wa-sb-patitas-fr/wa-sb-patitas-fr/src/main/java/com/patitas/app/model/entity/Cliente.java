package com.patitas.app.model.entity;

import java.io.Serializable;

public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; 
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String email;
    private String telefono;
    private Boolean estado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Cliente(Integer id, String nombre, String apellidos, String dni, String direccion, String email,
			String telefono, Boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.estado = estado;
	}
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public String toString() {
		return id +" "+nombre+" " + apellidos +" "+ dni +" "+ email +" "+ telefono +" "+ direccion +" "+ estado +" ";
		
	}

		

}
