package com.patitas.api.app.infraestructura.mapeadores.autenticacion;

import com.patitas.api.app.dominio.entidades.archMaestro.Accesos;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.AccesosJpa;

public class AccesosMaper {
	public static  AccesosJpa mapDeDominioAEntidadJpa(Accesos access) {
		return new AccesosJpa(
				access.getId(),
				access.getGestionCitas(),
				access.getGestionAtencion(),
				access.getGestionAdmision(),
				access.getGestionSeguridad());
	}
	
	public  static Accesos mapDeEntidadJpaADominio(AccesosJpa accessJpa) {
		return new Accesos(
				accessJpa.getId(),
				accessJpa.getGestionCitas(),
				accessJpa.getGestionAtencion(),
				accessJpa.getGestionAdmision(),
				accessJpa.getGestionSeguridad());
	}
}
