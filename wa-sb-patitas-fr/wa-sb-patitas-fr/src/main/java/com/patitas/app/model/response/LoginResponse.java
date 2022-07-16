package com.patitas.app.model.response;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class LoginResponse {
	private Boolean esAutenticado;
	private String username;
	private Map<String, Object> accesos;
	private String mensaje;
	@Override
	public String toString() {
		return "LoginResponse [esAutenticado=" + esAutenticado + ", username=" + username + ", accesos=" + accesos
				+ ", mensaje=" + mensaje + "]";
	}
	
	
	
	

}
