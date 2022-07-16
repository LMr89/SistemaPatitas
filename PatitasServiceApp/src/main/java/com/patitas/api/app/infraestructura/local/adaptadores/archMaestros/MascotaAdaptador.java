package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IMascota;
import com.patitas.api.app.dominio.entidades.archMaestro.Mascota;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.MascotaPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.archMaestros.MascotaRespuesta;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.MascotaJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.ClienteRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.EspecieRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.MascotaRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.RazaRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.MascotaMapper;

@Service
public class MascotaAdaptador implements IMascota{
	@Autowired
	private MascotaRep objMasctaRep;
	
	@Autowired 
	private ClienteRep objClienteRep;
	
	@Autowired 
	private RazaRep objRazaRep;
	@Autowired 
	private EspecieRep objEspecieRep;
	

	/*Metodo por default*/
	@Override
	public List<Mascota> listarMascotas() {
		List<Mascota> lista  = objMasctaRep.findAll()
				.stream()
				.map(MascotaMapper::mapDeEntidadJpaADominio)
				.collect(Collectors.toList());
		
		return lista;
	}

	@Override
	public MascotaRespuesta crearNuevaMascota(MascotaPeticion masco) {
		objMasctaRep.registrarMascota(masco.getIdCliente(), masco.getNombre(), 
				masco.getIdRaza(),masco.getColor(),masco.getIdEspecie(),true);
		
		return new MascotaRespuesta(masco.getNombre());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MascotaRespuesta> encontrarMascotaPorIdCliente(Integer id) {
		return  objMasctaRep.findAll().stream()
				.filter(m -> m.getIdCliente().getIdcliente() == id)
				.map(MascotaMapper::mapDeEntidadJpaAMascotaRespuesta)
				.collect(Collectors.toList());
	}

	@Override
	public MascotaRespuesta actualizarMascota(MascotaPeticion masco) {
		MascotaJpa objMascotaActualizar = new MascotaJpa(
				masco.getId(),
				objClienteRep.findById(masco.getIdCliente()).get(),
				masco.getNombre(),
				objRazaRep.findById(masco.getIdRaza()).get(),
				masco.getColor(),
				objEspecieRep.findById(masco.getIdEspecie()).get(),
				true
				
				);
		/*Se procede a actualizar la mascota */
		
		MascotaRespuesta resp= MascotaMapper.mapDeEntidadJpaAMascotaRespuesta(objMasctaRep.save(objMascotaActualizar));
		
		return resp ;
	}

	@Override
	public void eliminarMascota(Integer id) {
		objMasctaRep.delete(objMasctaRep.findById(id).get());
		
	}
	
	

	
	

}
