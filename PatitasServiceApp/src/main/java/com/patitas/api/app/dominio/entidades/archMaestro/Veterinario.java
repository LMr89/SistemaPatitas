package com.patitas.api.app.dominio.entidades.archMaestro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {
	private Integer id;
	private Usuario idUSuario;
	private Boolean estado;

}
