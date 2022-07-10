package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;

public class VeterinarioMapper {
	public static  VeterinarioJpa mapDeDominioAEntidadJpa(Veterinario vet) {
		return new VeterinarioJpa(
				vet.getId(),
				UsuarioMapper.mapDeDominioAEntidadJpa(vet.getIdUSuario())
			
				);
	}
	
	public  static Veterinario mapDeEntidadJpaADominio(VeterinarioJpa vetJpa) {
		return new Veterinario(
				vetJpa.getId(),
				UsuarioMapper.mapDeEntidadJpaADominio(vetJpa.getIdUsu())
				);
	}

}
