package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.HistoriaClinica;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HistoriaClinicaJpa;

public class HistoriaClinicaMapper {
	public static  HistoriaClinica mapDeEntidadJpaADominio(HistoriaClinicaJpa cliEnti) {
		
		return new HistoriaClinica(
				cliEnti.getId(),
				MascotaMapper.mapDeEntidadJpaADominio(cliEnti.getIdMascota()),
				cliEnti.getFechaCreacion(),
				cliEnti.getEstado()
				
				);
	}
	
	public  static HistoriaClinicaJpa mapDeDominioAEntidadJpa(HistoriaClinica clieDom) {
		return new HistoriaClinicaJpa(
				clieDom.getId(),
				MascotaMapper.mapDeDominioAEntidadJpa(clieDom.getIdMascota()),
				clieDom.getFechaCreacion(),
				clieDom.getEstado()
				);
	}


}
