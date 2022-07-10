package com.patitas.api.app.dominio.entidades.archMaestro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Accesos {
	private Integer id;
	private Boolean gestionCitas;
	private Boolean gestionAtencion;
	private Boolean gestionAdmision;
	private Boolean gestionSeguridad;

}
