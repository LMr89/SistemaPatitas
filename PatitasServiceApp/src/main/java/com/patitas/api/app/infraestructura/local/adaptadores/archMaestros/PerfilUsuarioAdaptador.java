package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IPerfilUsuario;
import com.patitas.api.app.dominio.entidades.archMaestro.PerfilUsuario;
import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.PerfilUsuarioPeticion;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.PerfilUsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.PerfilUsuarioRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.PerfilUsuarioMapper;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.UsuarioMapper;

@Service     
public class PerfilUsuarioAdaptador implements IPerfilUsuario {
	@Autowired
	private PerfilUsuarioRep perfilRep;

	@Override
	public PerfilUsuario crearNuevoPerfil(PerfilUsuarioPeticion perf) {
		perfilRep.registrarNuevoPerfil(
				perf.getNombre(),perf.getContraseña(), perf.getIdAcceso(), perf.getIdUsuario());
		return  new PerfilUsuario();
		
	}

	/*Se buscar por id del usuario*/
	@Override
	public Optional<PerfilUsuario> encontrarPerfilPorIdUSuario(Usuario id) {
		Optional<PerfilUsuarioJpa> encontrado = perfilRep.findByUsuarioEquals(
				UsuarioMapper.mapDeDominioAEntidadJpa(id));
		
		if (encontrado.isPresent()) {
			return Optional.of(PerfilUsuarioMapper.mapDeEntidadJpaADominio(
					encontrado.get()));
		}else {
			return Optional.empty();
		}
		
		
	
	}

	@Override
	public PerfilUsuario actualizarPerfil(PerfilUsuarioPeticion perf) {
		 perfilRep.actualizarPerfil(perf.getNombre(),perf.getContraseña(), perf.getIdAcceso(), perf.getIdUsuario());
		return new PerfilUsuario();
	}

	@Override
	public void eliminarPerfil(Integer id) {
		perfilRep.delete(perfilRep.findById(id).get());
		
	}

	
	
	

}
