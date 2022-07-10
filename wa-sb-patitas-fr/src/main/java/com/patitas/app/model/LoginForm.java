package com.patitas.app.model;

import java.io.Serializable;

public class LoginForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginForm(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}
	

}
