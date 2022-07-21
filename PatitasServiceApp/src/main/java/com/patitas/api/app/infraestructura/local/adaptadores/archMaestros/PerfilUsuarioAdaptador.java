package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

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
						UsuarioMapper.mapDeEntidadJpaADominio(usuarioRep.findById(perf.getIdUsuario()).get()), true));

			default:
				log.warn("IdAcceso no esta en el rango 1-2-3");;
				break;
			}
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
		
		Integer tipoDeUsuarioCambiado = perf.getIdAcceso();
		
		if (tipoDeUsuarioCambiado != obtenerPerfilActual(perf.getIdUsuario())) {
			
			
		}

		return new PerfilUsuario();
	}

	@Override
	public void eliminarPerfil(Integer id) {
		PerfilUsuario encontradorPorIdUsuario = encontrarPerfilPorIdUSuario(new Usuario(id)).get();
		perfilRep.delete(perfilRep.findById(encontradorPorIdUsuario.getId()).get());

	}

	@SuppressWarnings("unused")
	private Integer obtenerPerfilActual(Integer id) {
		/*
		 * 1 -> Administrador 2 -> Recepcionista 3 -> Veterinario
		 */

		Integer numeroDePerfil = 0;

		if (adminAdaptador.encontrarAdminPorIdUsuario(id).isPresent()) {

			numeroDePerfil = 1;
		}
		if (recepcionistaAdaptador.encontrarRecepcionistaPorIdUsuario(id).isPresent()) {

			numeroDePerfil = 2;
		}
		if (veterinarioAdaptador.encontrarVeterinarioPorIdCliente(id).isPresent()) {

			numeroDePerfil = 3;
		}

		return numeroDePerfil;

	}

}
