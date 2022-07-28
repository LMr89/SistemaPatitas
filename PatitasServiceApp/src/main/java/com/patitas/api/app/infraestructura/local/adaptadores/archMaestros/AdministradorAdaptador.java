package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.dominio.modelo.peticion.archMaestros.PerfilUsuarioPeticion;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.AdministradorJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.AdministradorRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.UsuarioRep;

@Service
public class AdministradorAdaptador {
	
	@Autowired
	private AdministradorRep adminiRep;
	
	@Autowired 
	private UsuarioRep usuarioRep;
	
	public Optional<AdministradorJpa> encontrarAdminPorIdUsuario(Integer id){
		UsuarioJpa usuarioJpa =usuarioRep.findById(id).get();
		return adminiRep.findByIdUsuario(usuarioJpa);
		
		
	}
	
	
	public Boolean registrarAdmin(PerfilUsuarioPeticion prf) {
		Boolean registrado = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(prf.getIdUsuario());
			AdministradorJpa nuevoAdmin = null;
			
			if (usuarioEncontrado.isPresent()) {
				nuevoAdmin = new AdministradorJpa(0, usuarioEncontrado.get(), true);
				adminiRep.save(nuevoAdmin);
				registrado = true;
				
			}
		} catch (Exception e) {
			System.out.println("Error en la clase Admin Adaptador: -> " + e.getMessage());
			
		}
		
		return registrado;
	}
	public Boolean actualizarAdmin(Integer idUsuario) {
		Boolean actualizado = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(idUsuario);
			AdministradorJpa repParaActualizar = null;
			
			if (usuarioEncontrado.isPresent()) {
				repParaActualizar = adminiRep.findByIdUsuario(usuarioEncontrado.get()).get();
				repParaActualizar.setEstado(true);
				adminiRep.save(repParaActualizar);
				actualizado = true;
				
			}
		} catch (Exception e) {
			System.out.println("Error en la clase Recepcionista Adaptador: -> " + e.getMessage());
			
		}
		
		return actualizado;
	}
	
	public Boolean desactivarAdmin(Integer id) {
		Boolean eliminado = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(id);

			AdministradorJpa repParaEliminar = null;
			
			if (usuarioEncontrado.isPresent()) {
				repParaEliminar =  adminiRep.findByIdUsuario(usuarioEncontrado.get()).get();
				/*SE le cambia el estado*/
				repParaEliminar.setEstado(false);
				adminiRep.save(repParaEliminar);
				eliminado = true;
				
			}
			System.out.println("SE DESACTIVO EL ADMINISTRADOR");
		} catch (Exception e) {
			System.out.println("Error en la clase Admin Adaptador: -> " + e.getMessage());
			
		}
		
		return eliminado;
	}
}
