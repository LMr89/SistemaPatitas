package com.patitas.api.app.dominio.objetosValores;

import lombok.Data;


public enum LoginConstantes {
	AUTORIZADO(true, "Acceso valido"),
	NO_AUTORIZADO(false, "Acceso denegado");
	
	public Boolean esAuth;
	public String msg;
	
	private LoginConstantes(Boolean esAuth, String msg) {
		this.esAuth = esAuth;
		this.msg = msg;
	}

	public Boolean getEsAuth() {
		return esAuth;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
	
	
	
}
