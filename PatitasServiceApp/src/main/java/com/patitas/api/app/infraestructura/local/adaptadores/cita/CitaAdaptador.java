package com.patitas.api.app.infraestructura.local.adaptadores.cita;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.cita.ICita;
import com.patitas.api.app.dominio.entidades.cita.Cita;
import com.patitas.api.app.dominio.modelo.peticion.cita.CitaPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.cita.CitaRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.cita.CitaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.ClienteRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.MascotaRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.RecepcionistaRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.VeterinarioRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.cita.CitaRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.UsuarioMapper;
import com.patitas.api.app.infraestructura.mapeadores.cita.CitaMapeador;

@SuppressWarnings("unused")
@Service
public class CitaAdaptador  implements ICita{
	
	
	@Autowired
	private CitaRep citaRep;
	@Autowired
	private MascotaRep mascotaRep;
	@Autowired
	private RecepcionistaRep recepRep;
	@Autowired
	private ClienteRep cliRep;
	@Autowired
	private VeterinarioRep vetRep;

	@Override
	public List<Cita> listarCitas() {
		List<Cita> citaObtenidas = citaRep.findAll()
				.stream()
				.map(CitaMapeador::mapDeEntidadJpaADominio)
				.collect(Collectors.toList());
		
		return citaObtenidas;
	}

	@Override
	public CitaRespuesta crearNuevaCita(CitaPeticion cita) {
		 citaRep.registrarCita(
				cita.getIdCliente(),
				cita.getIdMascota(),
				cita.getIdVeterinario(),
				cita.getIdRecepcionista(),
				cita.getFechaRegistro(),
				cita.getFechaAtencion(),
				cita.getPendiente()
				);
		return  new CitaRespuesta();
	}

	@Override
	public Optional<Cita> encontrarCitaPorId(Integer id) {
		return Optional.of(CitaMapeador.mapDeEntidadJpaADominio(
				citaRep.findById(id).get()));
	}

	@Override
	public CitaRespuesta actualizarCita(CitaPeticion cita) {
		CitaJpa citaParaActualizar = new CitaJpa(
				cita.getIdCita(),
				cliRep.findById(cita.getIdCliente()).get(),
				mascotaRep.findById(cita.getIdMascota()).get(),
				vetRep.findById(cita.getIdVeterinario()).get(),
				recepRep.findById(cita.getIdRecepcionista()).get(),
				cita.getFechaRegistro(),
				cita.getFechaAtencion(),
				cita.getPendiente(),
				true
				);
		
		
		CitaJpa usuarioActualizado = citaRep.save(citaParaActualizar);
		return  CitaMapeador.mapDeDominioACitaRespuesta(
				CitaMapeador.mapDeEntidadJpaADominio(usuarioActualizado));
	}

	@Override
	public void eliminarCita(Integer id) {
		
		
		
		citaRep.delete(CitaMapeador.mapDeDominioAEntidadJpa(
				encontrarCitaPorId(id).get()));
		
	}
	

}
