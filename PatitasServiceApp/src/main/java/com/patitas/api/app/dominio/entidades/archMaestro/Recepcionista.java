package com.patitas.api.app.dominio.entidades.archMaestro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recepcionista {
	private Integer id;
	private Usuario idUsuario;

}
