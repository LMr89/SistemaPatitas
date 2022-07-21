package com.patitas.api.app.infraestructura.mapeadores.archMaestros;


import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;

public class VeterinarioMapper {
	public static  VeterinarioJpa mapDeDominioAEntidadJpa(Veterinario vet) {
		return new VeterinarioJpa(
				vet.getId(),
				UsuarioMapper.mapDeDominioAEntidadJpa(vet.getIdUSuario()),
				vet.getEstado()
	
			
				);
	}
	
	public  static Veterinario mapDeEntidadJpaADominio(VeterinarioJpa vetJpa) {
		return new Veterinario(
				vetJpa.getId(),
				UsuarioMapper.mapDeEntidadJpaADominio(vetJpa.getIdUsu()),
				vetJpa.getEstado()
				);
	}
	
	public static VeterinarioRespuesta  mapDeEntidadJpaAVeterinarioRespuesta(VeterinarioJpa vetJpa) {
		return new VeterinarioRespuesta(vetJpa.getId()
				,vetJpa.getIdUsu().getNombre(),
				vetJpa.getIdUsu().getApellidos(),
				vetJpa.getLstHorarios()
					);
	}

}
