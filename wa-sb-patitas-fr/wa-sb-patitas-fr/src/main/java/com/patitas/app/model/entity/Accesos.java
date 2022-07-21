package com.patitas.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accesos {
	
	private Integer id;
    private Boolean gestionCitas;
    private Boolean gestionAtencion;
    private Boolean gestionAdmision;
    private Boolean gestionSeguridad;

}
