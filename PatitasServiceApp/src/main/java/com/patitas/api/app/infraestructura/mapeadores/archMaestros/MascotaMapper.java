package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.Mascota;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.MascotaRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.EspecieJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.RazaJpa;

public class MascotaMapper {
	public static  MascotaJpa mapDeDominioAEntidadJpa(Mascota mascota) {
		return new MascotaJpa(
				mascota.getId(),
				ClienteMaper.mapDeDominioAEntidadJpa(mascota.getIdCliente()),
				mascota.getNombre(),
				new RazaJpa(mascota.getRaza()),
				mascota.getColor(),
				new EspecieJpa(mascota.getEspecie()),
				mascota.getEstado()
				
				);
	}
	
	public  static Mascota mapDeEntidadJpaADominio(MascotaJpa mascotaJpa) {
		return new Mascota(
				mascotaJpa.getId(),
				ClienteMaper.mapDeEntidadJpaADominio(mascotaJpa.getIdCliente()),
				mascotaJpa.getNombre(),
				mascotaJpa.getRaza().getIdRaza(),
				mascotaJpa.getColor(),
				mascotaJpa.getEspecie().getIdEspecie(),
				mascotaJpa.getEstado()
				);
	}
	
	public  static MascotaRespuesta mapDeEntidadJpaAMascotaRespuesta(MascotaJpa mascotaJpa) {
		return new MascotaRespuesta(
				mascotaJpa.getId(),
				mascotaJpa.getNombre(),
				mascotaJpa.getRaza().getNombre(),
				mascotaJpa.getColor(),
				mascotaJpa.getEspecie().getNombre()
				);
	}
}
