package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IHistoriaClinica;
import com.patitas.api.app.dominio.entidades.archMaestro.HistoriaClinica;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.HistoriaClinicaPeticion;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.HistoriaClinicaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.HistoriaClinicaRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.MascotaRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.HistoriaClinicaMapper;


@Service
public class HistoriaClinicaAdaptador implements IHistoriaClinica {
	@Autowired
	private HistoriaClinicaRep historiaClinicaRep;
	
	@Autowired
	private MascotaRep mascotaRep;
	
	@Override
	public Boolean crearNuevaHistoria(HistoriaClinicaPeticion cli) {
		Boolean creado = false;
		try {
			Optional<HistoriaClinicaJpa> existeYaUnaHistoria = historiaClinicaRep.findByIdMascota(
					mascotaRep.findById(cli.getIdMascota()).get());
			
			if (existeYaUnaHistoria.isPresent()) {
				/*Si ya existe tan solo es modificar su estado a true*/
				existeYaUnaHistoria.get().setEstado(true);
				
				creado = true;
				historiaClinicaRep.save(existeYaUnaHistoria.get());
			}else {
				/*Si no existe se procede a crearlo*/
				HistoriaClinicaJpa nuevaHistoria = new HistoriaClinicaJpa(
						cli.getIdHistoria(),
						mascotaRep.findById(cli.getIdMascota()).get(),
						Calendar.getInstance(),
						true
						);
				
				historiaClinicaRep.save(nuevaHistoria);
				creado = true;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creado;
	}

	@Override
	public Optional<HistoriaClinica> encontrarHistoriaPorIdMascota(Integer id) {
		Optional<HistoriaClinicaJpa> encontrado = historiaClinicaRep.findByIdMascota(
				mascotaRep.findById(id).get());
		
		if (encontrado.isPresent()) {
			return Optional.of(HistoriaClinicaMapper.mapDeEntidadJpaADominio(encontrado.get()));
		}
		return Optional.empty();
	}

	@Override
	public Boolean actualizarHistoria(HistoriaClinicaPeticion cli) {
		/*Método sin implementar ya que por el momento no se actualizara la historia clinica*/
		return null;
	}

	@Override
	public Boolean eliminarHistoria(Integer id) {
		Boolean eliminado = false;
		Optional<HistoriaClinicaJpa> encontrarHistoriaParaEliminar  = historiaClinicaRep.findById(id);
		
		if (encontrarHistoriaParaEliminar.isPresent()) {
			encontrarHistoriaParaEliminar.get().setEstado(false);
			historiaClinicaRep.save(encontrarHistoriaParaEliminar.get());
		}
		return eliminado;
	}
	
	

}
