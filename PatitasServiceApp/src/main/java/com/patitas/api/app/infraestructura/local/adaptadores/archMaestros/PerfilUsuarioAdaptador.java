package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IPerfilUsuario;
import com.patitas.api.app.dominio.entidades.archMaestro.PerfilUsuario;
import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;
import com.patitas.api.app.dominio.entidades.archMaestro.Veterinario;
import com.patitas.api.app.dominio.modelo.peticion.archMaestros.PerfilUsuarioPeticion;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.PerfilUsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.PerfilUsuarioRep;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.UsuarioRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.PerfilUsuarioMapper;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.UsuarioMapper;

@Service
public class PerfilUsuarioAdaptador implements IPerfilUsuario {
	private static Logger log = LogManager.getLogger(PerfilUsuarioAdaptador.class);
	@Autowired
	private PerfilUsuarioRep perfilRep;

	@Autowired
	private AdministradorAdaptador adminAdaptador;

	@Autowired
	private RecepcionistaAdaptador recepcionistaAdaptador;

	@Autowired
	private VeterinarioAdaptador veterinarioAdaptador;

	@Autowired
	private UsuarioRep usuarioRep;

	@Override
	public PerfilUsuario crearNuevoPerfil(PerfilUsuarioPeticion perf) {
		try {
			perfilRep.registrarNuevoPerfil(perf.getNombre(), perf.getContraseña(), perf.getIdAcceso(),
					perf.getIdUsuario());

			registrarEnTablaARV(perf);

		} catch (Exception e) {
			System.out.println("Error en Perfil adpatador: " + e.getMessage());
		}

		return new PerfilUsuario();

	}

	/* Se buscar por id del usuario */
	@Override
	public Optional<PerfilUsuario> encontrarPerfilPorIdUSuario(Usuario id) {
		Optional<PerfilUsuarioJpa> encontrado = perfilRep
				.findByUsuarioEquals(UsuarioMapper.mapDeDominioAEntidadJpa(id));

		if (encontrado.isPresent()) {
			return Optional.of(PerfilUsuarioMapper.mapDeEntidadJpaADominio(encontrado.get()));
		} else {
			return Optional.empty();
		}

	}

	@Override
	public PerfilUsuario actualizarPerfil(PerfilUsuarioPeticion perf) {
		/*PENDIENTE POR HACER*/
		perfilRep.actualizarPerfil(perf.getIdPerfil(), perf.getNombre(), perf.getContraseña(), perf.getIdAcceso(),
				perf.getIdUsuario());
		

		Integer nuevoIdAcceso = perf.getIdAcceso();


		/*En el caso de que el usuario este registrado en otras tablas
		* 	recepcionista, veterinario, etc , se procede a buscarlo
		* 	y desactivarlo */

		for (HashMap.Entry<Integer, Boolean> perfile:
				obtenerPerfilesActuales(perf.getIdUsuario()).entrySet() ) {


			if (perfile.getKey() == nuevoIdAcceso){

				/*Si el perfil esta registrado en la tabla V,R,A solo se procede
				* 	a cambiar su estado a true*/

				if (perfile.getValue() == true){
					activarRegistro(perf);
				}else{
					/*En el caso en que no este en las tablas VETERINRAIO,
					* RECEPCIONISTA O ADMINISTRAR DE PROSIGUE A CREARLE*/
					registrarEnTablaARV(perf);
				}

			}else{
				if (perfile.getValue() == true){
					/*Para los demas registros prosigo a eliminarlos, mandandole al mètodo el rol actual en secuencia
					y la clase PerfilPeticion para obtener el idUSuario para luego desactivarlo
					 * mejor dicho a desactivarlos*/
					desactivarRegistroEnTablas(perfile.getKey(), perf);
				}
			}
		}
		return new PerfilUsuario();
	}

	@Override
	public void eliminarPerfil(Integer idUsuario) {
		PerfilUsuario encontradorPorIdUsuario =
				encontrarPerfilPorIdUSuario(new Usuario(idUsuario)).get();
		perfilRep.delete(perfilRep.findById(encontradorPorIdUsuario.getId()).get());

		/*Se desactiva sus registros en las tablas V , A , R*/
		for (HashMap.Entry<Integer, Boolean> perfile:
				obtenerPerfilesActuales(idUsuario).entrySet() ){

			if (perfile.getValue() == true){
					/*Para los demas registros prosigo a eliminarlos, mandandole al mètodo el rol actual en secuencia
					y la clase PerfilPeticion para obtener el idUSuario para luego desactivarlo
					 * mejor dicho a desactivarlos*/
				desactivarRegistroEnTablas(
						perfile.getKey(),
						PerfilUsuarioMapper.mapDeDominioAPeticion(encontradorPorIdUsuario));
			}
		}

	}

	@SuppressWarnings("unused")
	private Map<Integer, Boolean> obtenerPerfilesActuales(Integer idUsuario) {
		/*
		 * 1 -> Administrador 2 -> Recepcionista 3 -> Veterinario
		 */
		Map<Integer, Boolean> perfiles = new HashMap() {{
			put(1, false);
			put(2, false);
			put(3, false);
		}};

		if (adminAdaptador.encontrarAdminPorIdUsuario(idUsuario).isPresent()) {
			perfiles.replace(1, true);
		}
		if (recepcionistaAdaptador.encontrarRecepcionistaPorIdUsuario(idUsuario).isPresent()) {
			perfiles.replace(2, true);
		}
		if (veterinarioAdaptador.encontrarVeterinarioPorIdCliente(idUsuario).isPresent()) {
			perfiles.replace(3 , true);

		}

		System.out.println(perfiles);
		return perfiles;
	}

	private Boolean desactivarRegistroEnTablas(Integer rolEnSecuencia , PerfilUsuarioPeticion perf){
		/*Este método desactivara al usuario */
		Boolean desactivado = false;
		try {
			/*
			 * 1 -> Administrador 2 -> Recepcionista 3 -> Veterinario
			 */
			switch (rolEnSecuencia){
				/*Actualizar el rol osea cambiar  su estado a true */
				case 1 :
					adminAdaptador.desactivarAdmin(perf.getIdUsuario());
					break;
				case 2 :
					recepcionistaAdaptador.desactivarRecepcionista(perf.getIdUsuario());
					break;
				default:
					veterinarioAdaptador.desactivarVeterinario(perf.getIdUsuario());
					break;
			}

			desactivado = true;
			log.info("Se actualizo el rol numero : " + perf.getIdAcceso());

		}catch (Exception e ){
			log.error("Ocurrio un error: " + e.getMessage());

		}


		return desactivado;
	}

	private Boolean activarRegistro(PerfilUsuarioPeticion perf){
		Boolean activado = false;
		try {
			/*
			 * 1 -> Administrador 2 -> Recepcionista 3 -> Veterinario
			 */
			switch (perf.getIdAcceso()){
				/*Actualizar el rol osea cambiar  su estado a true */
				case 1 :
					adminAdaptador.actualizarAdmin(perf.getIdUsuario());
					break;

				case 2 :
					recepcionistaAdaptador.actualizarRecepcionista(perf.getIdUsuario());
					break;
				default:
					veterinarioAdaptador.actualizarVeterinario(perf.getIdUsuario());
					break;
			}

			activado = true;
			log.info("Se actualizo el rol numero : " + perf.getIdAcceso());

		}catch (Exception e ){
			log.error("Ocurrio un error: " + e.getMessage());

		}


		return activado;
	}

	private Boolean registrarEnTablaARV(PerfilUsuarioPeticion perf){
		Boolean registrado = false;
		try{
			switch (perf.getIdAcceso()) {
				case 1:
					// Registrar un nuevo administrador en la tabla de administradores
					adminAdaptador.registrarAdmin(perf);
					break;
				case 2:
					// Registrar una recepcionista en la tabla de administradores
					recepcionistaAdaptador.registrarRecepcionista(perf);
					break;
				case 3:
					// Registrar una recepcionista en la tabla de administradores
					veterinarioAdaptador.crearNuevoVeterinario(new Veterinario(0,
							UsuarioMapper.mapDeEntidadJpaADominio(usuarioRep.findById(perf.getIdUsuario()).get()),
							true));
					break;

				default:
					log.warn("IdAcceso no esta en el rango 1-2-3");;
					break;
			}
			registrado = true;

		}catch (Exception e ){
			log.error( "Hubo un error en registrar en al tabla ARV: " + e.getMessage());

		}
		return  registrado;

	}


}
