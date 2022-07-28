package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IVeterinario;
import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.UsuarioRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.VeterinarioRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.VeterinarioMapper;

@Service
public class VeterinarioAdaptador implements IVeterinario{
	@Autowired
	private VeterinarioRep vetRep;
	
	@Autowired
	private UsuarioRep usuarioRep;

	@Override
	public List<VeterinarioRespuesta> listarVeterinario() {
		List<VeterinarioRespuesta> listado = 
				vetRep.findAll()
				.stream()
				.filter(v -> v.getEstado() == true)
				.map(VeterinarioMapper::mapDeEntidadJpaAVeterinarioRespuesta)
				.collect(Collectors.toList());
		
		return listado;
	}

	@Override
	public VeterinarioRespuesta crearNuevoVeterinario(Veterinario vet) {
		
		VeterinarioJpa veterinarioCreado = vetRep.save(VeterinarioMapper.mapDeDominioAEntidadJpa(vet));
		return  VeterinarioMapper.mapDeEntidadJpaAVeterinarioRespuesta(veterinarioCreado);
	}

	@Override
	public Optional<Veterinario> encontrarVeterinarioPorIdCliente(Integer id) {
		UsuarioJpa usuarioJpa =usuarioRep.findById(id).get();
		return Optional.of(VeterinarioMapper.mapDeEntidadJpaADominio(
				vetRep.findByIdUsu(usuarioJpa).get()));
	}

	@Override
	public Boolean actualizarVeterinario(Integer idUsuario) {

		Boolean actualizado = false;
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(idUsuario);
			VeterinarioJpa repParaActualizar = null;
			if (usuarioEncontrado.isPresent()) {
				repParaActualizar = vetRep.findByIdUsu(usuarioEncontrado.get()).get();
				repParaActualizar.setEstado(true);
				vetRep.save(repParaActualizar);
				actualizado = true;

			}
		} catch (Exception e) {
			System.out.println("Error en la clase Recepcionista Adaptador: -> " + e.getMessage());

		}

		return actualizado ;
	}

	@Override
	public void desactivarVeterinario(Integer idUsuario) {
		try {
			Optional<UsuarioJpa> usuarioEncontrado  =  usuarioRep.findById(idUsuario);
			VeterinarioJpa repParaEliminar = null;
			
			if (usuarioEncontrado.isPresent()) {
				repParaEliminar = vetRep.findByIdUsu(usuarioEncontrado.get()).get();
				repParaEliminar.setEstado(false);
				vetRep.save(repParaEliminar);
				
				
			}
		} catch (Exception e) {
			System.out.println("Error en la clase Veterinario Adaptador: -> " + e.getMessage());
			
		}
		
	}
		
	
	

}
