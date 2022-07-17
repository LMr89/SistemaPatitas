package com.patitas.api.app.infraestructura.mapeadores.cita;

import com.patitas.api.app.dominio.entidades.cita.Cita;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.ClienteRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.MascotaRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.RecepcionistaRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;
import com.patitas.api.app.dominio.modelo.respuesta.cita.CitaRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.cita.CitaJpa;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.ClienteMaper;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.MascotaMapper;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.RecepcionistaMapper;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.VeterinarioMapper;

public class CitaMapeador {
	public static  CitaJpa mapDeDominioAEntidadJpa(Cita cita) {
		return new CitaJpa(
				cita.getId(),
				ClienteMaper.mapDeDominioAEntidadJpa(cita.getIdCliente()),
				MascotaMapper.mapDeDominioAEntidadJpa(cita.getIdMascota()),
				VeterinarioMapper.mapDeDominioAEntidadJpa(cita.getIdVeterinario()),
				RecepcionistaMapper.mapDeDominioAEntidadJpa(cita.getIdRecepcionista()),
				cita.getFechaRegistro(),
				cita.getFechaAtencion(),
				cita.getPendiente()
				);
	}
	
	public  static Cita mapDeEntidadJpaADominio(CitaJpa citaJpa) {
		return new Cita(
				citaJpa.getIdCita(),
				ClienteMaper.mapDeEntidadJpaADominio(citaJpa.getIdCliente()),
				MascotaMapper.mapDeEntidadJpaADominio(citaJpa.getIdMascota()),
				VeterinarioMapper.mapDeEntidadJpaADominio(citaJpa.getIdVeterinario()),
				RecepcionistaMapper.mapDeEntidadJpaADominio(citaJpa.getIdRecepcionista()),
				citaJpa.getFechaRegistro(),
				citaJpa.getFechaAtencion(),
				citaJpa.getPendiente()
				);
	}
	public static CitaRespuesta mapDeDominioACitaRespuesta(Cita cita) {
		return new CitaRespuesta(
				cita.getId(),
				new ClienteRespuesta(cita.getIdCliente().getId(), cita.getIdCliente().getNombre(),cita.getIdCliente().getApellidos()),
				new MascotaRespuesta(cita.getIdMascota().getId(), cita.getIdMascota().getNombre()),
				new VeterinarioRespuesta(cita.getIdVeterinario().getId(), cita.getIdVeterinario().getIdUSuario().getNombre(), cita.getIdVeterinario().getIdUSuario().getApellidos()),
				new RecepcionistaRespuesta(cita.getIdRecepcionista().getId(), cita.getIdRecepcionista().getIdUsuario().getNombre()),
				cita.getFechaRegistro(),
				cita.getFechaAtencion(),
				cita.getPendiente()		
				);
	}
}
