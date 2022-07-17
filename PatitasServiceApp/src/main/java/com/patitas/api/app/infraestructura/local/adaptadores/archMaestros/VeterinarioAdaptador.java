package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IVeterinario;
import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.VeterinarioRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.VeterinarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.VeterinarioRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.VeterinarioMapper;

@Service
public class VeterinarioAdaptador implements IVeterinario{
	@Autowired
	private VeterinarioRep vetRep;

	@Override
	public List<VeterinarioRespuesta> listarVeterinario() {
		List<VeterinarioRespuesta> listado = 
				vetRep.findAll()
				.stream()
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
		return Optional.of(VeterinarioMapper.mapDeEntidadJpaADominio(
				vetRep.findById(id).get()));
	}

	@Override
	public Veterinario actualizarVeterinario(Veterinario vet) {
		VeterinarioJpa veterinarioActualizado = vetRep.save(VeterinarioMapper.mapDeDominioAEntidadJpa(vet));
		return  VeterinarioMapper.mapDeEntidadJpaADominio(veterinarioActualizado);
	}

	@Override
	public void eliminarVeterinario(Integer id) {
		vetRep.delete(VeterinarioMapper.mapDeDominioAEntidadJpa(
				encontrarVeterinarioPorIdCliente(id).get()));
		
	}
	

}
