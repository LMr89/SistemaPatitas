package com.patitas.api.app.dominio.entidades;

import lombok.Data;

@Data
public class Accesos {
	private Integer id;
	private Boolean gestionCitas;
	private Boolean gestionAtencion;
	private Boolean gestionAdmision;
	private Boolean gestionSeguridad;

}
