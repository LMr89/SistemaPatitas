package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.Horario;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HorarioJpa;

public class HorarioMapper {

	public static  HorarioJpa mapDeDominioAEntidadJpa(Horario horario) {
		return new HorarioJpa(
				horario.getIdHora(),
				VeterinarioMapper.mapDeDominioAEntidadJpa(horario.getIdVet()),
				horario.getHoraInicio(),
				horario.getHoraFin()
			
				);
	}
	
	public  static Horario mapDeEntidadJpaADominio(HorarioJpa horarioJpa) {
		return new Horario(
				horarioJpa.getId(),
				VeterinarioMapper.mapDeEntidadJpaADominio(horarioJpa.getIdVet()),
				horarioJpa.getFechaInicio(),
				horarioJpa.getFechaFin()
				
				);
	
	}
}