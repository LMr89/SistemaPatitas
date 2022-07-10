package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.Recepcionista;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.RecepcionistaJpa;


public class RecepcionistaMapper {
	public static  RecepcionistaJpa mapDeDominioAEntidadJpa(Recepcionista  recep) {
		return new RecepcionistaJpa(
				recep.getId(),
				UsuarioMapper.mapDeDominioAEntidadJpa(recep.getIdUsuario())
			
				);
	}
	
	public  static Recepcionista mapDeEntidadJpaADominio(RecepcionistaJpa recepJpa) {
		return new Recepcionista(
				recepJpa.getIdRecepcionista(),
				UsuarioMapper.mapDeEntidadJpaADominio(recepJpa.getIdUsuario())
				);
	}

}
