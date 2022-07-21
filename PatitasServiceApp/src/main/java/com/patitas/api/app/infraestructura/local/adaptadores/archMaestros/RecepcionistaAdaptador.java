package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.dominio.modelo.peticion.archMaestros.PerfilUsuarioPeticion;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.RecepcionistaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.RecepcionistaRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.UsuarioRep;

@Service
public class RecepcionistaAdaptador {
	@Autowired
	private RecepcionistaRep recepcionistaRep;
	
	@Autowired 
	private UsuarioRep usuarioRep;
	
	public Optional<RecepcionistaJpa> encontrarRecepcionistaPorIdUsuario(Integer id){
		UsuarioJpa usuarioJpa =usuarioRep.findById(id).get();
		return recepcionistaRep.findByIdUsuario(usuarioJpa);
		
		
	}
	
	public Boolean registrarRecepcionista(PerfilUsuarioPeticion prf) {
		Boolean registrada = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(prf.getIdUsuario());
			RecepcionistaJpa nuevaRecep = null;
			
			if (usuarioEncontrado.isPresent()) {
				nuevaRecep = new RecepcionistaJpa(0, usuarioEncontrado.get(), true);
				recepcionistaRep.save(nuevaRecep);
				registrada = true;
				
			}
		} catch (Exception e) {
			System.out.println("Error en la clase Recepcionista Adaptador: -> " + e.getMessage());
			
		}
		
		return registrada;
	}
	
	public Boolean actualizarRecepcionista(Integer id) {
		Boolean actualizado = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(id);
			RecepcionistaJpa repParaActualizar = null;
			
			if (usuarioEncontrado.isPresent()) {
				repParaActualizar = new RecepcionistaJpa(usuarioEncontrado.get().getId(), 
						usuarioEncontrado.get(), true);
				recepcionistaRep.save(repParaActualizar);
				actualizado = true;
				
			}
		} catch (Exception e) {
			System.out.println("Error en la clase Recepcionista Adaptador: -> " + e.getMessage());
			
		}
		
		return actualizado;
	}
	
	public Boolean eliminarrRecepcionista(Integer id) {
		Boolean eliminada = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(id);
			RecepcionistaJpa repParaEliminar = null;
			
			if (usuarioEncontrado.isPresent()) {
				repParaEliminar = new RecepcionistaJpa(usuarioEncontrado.get().getId(), 
						usuarioEncontrado.get(), false);
				recepcionistaRep.save(repParaEliminar);
				eliminada = true;
				
			}
		} catch (Exception e) {
			System.out.println("Error en la clase Recepcionista Adaptador: -> " + e.getMessage());
			
		}
		
		return eliminada;
	}
	
	

}
