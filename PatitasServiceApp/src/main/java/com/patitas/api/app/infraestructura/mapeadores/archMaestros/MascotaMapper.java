package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.Mascota;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;

public class MascotaMapper {
	public static  MascotaJpa mapDeDominioAEntidadJpa(Mascota mascota) {
		return new MascotaJpa(
				mascota.getId(),
				ClienteMaper.mapDeDominioAEntidadJpa(mascota.getIdCliente()),
				mascota.getNombre(),
				mascota.getRaza(),
				mascota.getColor(),
				mascota.getEspecie(),
				mascota.getEstado()
				
				);
	}
	
	public  static Mascota mapDeEntidadJpaADominio(MascotaJpa mascotaJpa) {
		return new Mascota(
				mascotaJpa.getId(),
				ClienteMaper.mapDeEntidadJpaADominio(mascotaJpa.getIdCli()),
				mascotaJpa.getNombre(),
				mascotaJpa.getRaza(),
				mascotaJpa.getColor(),
				mascotaJpa.getEspecie(),
				mascotaJpa.getEstado()
				);
	}
}
