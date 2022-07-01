package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.PerfilUsuario;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.PerfilUsuarioJpa;
import com.patitas.api.app.infraestructura.mapeadores.autenticacion.AccesosMaper;

public class PerfilUsuarioMapper {
	public static  PerfilUsuario mapDeEntidadJpaADominio(PerfilUsuarioJpa perfilEnt) {
		return new PerfilUsuario(
				perfilEnt.getId(),
				perfilEnt.getNombreUsuario(),
				perfilEnt.getContraseniaUsuario(),
				AccesosMaper.mapDeEntidadJpaADominio(perfilEnt.getAccesos()),
				UsuarioMapper.mapDeEntidadJpaADominio(perfilEnt.getUsuario()));
	}
	
	public  static PerfilUsuarioJpa mapDeDominioAEntidadJpa(PerfilUsuario perfilDom) {
		return new PerfilUsuarioJpa(
				perfilDom.getId(),
				perfilDom.getNombreUsuario(),
				perfilDom.getContraseniaUsuario(),
				AccesosMaper.mapDeDominioAEntidadJpa(perfilDom.getIdAcceso()),
				UsuarioMapper.mapDeDominioAEntidadJpa(perfilDom.getIdUSuario())
				);
	}
}
