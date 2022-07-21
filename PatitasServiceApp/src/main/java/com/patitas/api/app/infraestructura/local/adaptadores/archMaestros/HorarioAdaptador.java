package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;
import org.apache.logging.log4j.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.dominio.modelo.peticion.archMaestros.HorarioPeticion;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HorarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.HorarioRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.VeterinarioRep;

@Service
public class HorarioAdaptador {
	private  static  Logger log = LogManager.getLogger(HorarioAdaptador.class);
	
	@Autowired
	private HorarioRep horarioRep;
	
	@Autowired
	private VeterinarioRep veterinarioRep;
	
	public HorarioJpa obtenerHorarioDeUsuarioVet(Integer idUsuario){

		Optional<VeterinarioJpa > vetENcontrado = veterinarioRep.findByIdUsu(new UsuarioJpa(idUsuario));
		Optional<HorarioJpa> encontrado = horarioRep.findByIdVet(vetENcontrado.get());
		log.info("Veterinario encontrado: "  + vetENcontrado.toString());
		log.info("HOrario del veterinario encontrado: "  + encontrado.toString());

		if (encontrado.isPresent()){
			return  encontrado.get();
		}else{
			return  new HorarioJpa();
		}
	}

	@SuppressWarnings("deprecation")
	public Boolean registrarHorario(HorarioPeticion peticion) throws ConstraintViolationException {
		Boolean creado = false;
		try {
			horarioRep.registrarHorario(peticion.getIdVeterinario(),
					peticion.getManInicio().getHours()+":"+ peticion.getManInicio().getMinutes(),
					peticion.getManFin().getHours()+":"+ peticion.getManFin().getMinutes(),
					peticion.getTarInicio().getHours()+":"+ peticion.getTarInicio().getMinutes(),
					peticion.getTarFin().getHours()+":"+ peticion.getTarFin().getMinutes());

			creado = true;
			
		} catch (ConstraintViolationException e) {
			log.error("Hubo un error en el horario adaptador:  "+ e.getCause());
		}
		return creado;
	}

	
	
	public Boolean actualizarHorario(HorarioPeticion peticion) {
		Boolean actualizado = false;

		try {
			Optional<HorarioJpa> encontrado = Optional.of(obtenerHorarioDeUsuarioVet(peticion.getIdVeterinario()));

			if (encontrado.isPresent()){
				encontrado.get().setManInicio(peticion.getManInicio());
				encontrado.get().setManFin(peticion.getManFin());
				encontrado.get().setTarInicio(peticion.getTarInicio());
				encontrado.get().setTarFin(peticion.getTarFin());

				horarioRep.save(encontrado.get());
				actualizado = true;
			}
			
		} catch (Exception e) {
			log.error("Error en actualizar 'Horario Adaptador': " + e.getMessage());

		}
		return actualizado;
	}

	public Boolean eliminarHorario(Integer idUsuario) {
		Boolean eliminado = false;

		try {
			Optional<HorarioJpa> encontrado =
					Optional.of(obtenerHorarioDeUsuarioVet(idUsuario));

			if (encontrado.isPresent()){
				horarioRep.deleteById(encontrado.get().getId());
				eliminado = true;
			}

		} catch (Exception e) {
			log.error("Error en eliminar 'Horario Adaptador': " + e.getMessage());

		}
		return eliminado;
	}
	
	

}
