package com.patitas.api.app.infraestructura.local.adaptadores.autenticacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.autenticacion.ILogin;
import com.patitas.api.app.dominio.modelo.peticion.autenticacion.LoginPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.autenticacion.LoginRespuesta;
import com.patitas.api.app.dominio.objetosValores.LoginConstantes;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.PerfilUsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.PerfilUsuarioRep;
import com.patitas.api.app.infraestructura.mapeadores.autenticacion.AccesosMaper;

@Service
public class LoginAdapter implements ILogin{
	@Autowired
	private PerfilUsuarioRep perfilRep;
	
	@Override
	public LoginRespuesta iniciarSesion(LoginPeticion log) {
		LoginRespuesta logResp = null;
		Optional<PerfilUsuarioJpa> perfilEncontrado=  
				perfilRep.findByNombreUsuarioAndContraseniaUsuario(
						log.getNomUsuario(),
						log.getContrasenia());
		
		if (perfilEncontrado.isPresent()) {
			logResp = new LoginRespuesta(
					LoginConstantes.AUTORIZADO.getEsAuth(),
					perfilEncontrado.get().getNombreUsuario(),
					LoginConstantes.AUTORIZADO.getMsg(),
					
					AccesosMaper.mapDeEntidadJpaADominio(perfilEncontrado.get().getAccesos()));
		}else {
			logResp = new LoginRespuesta(
					LoginConstantes.NO_AUTORIZADO.getEsAuth(),
					"No encontrado",
					LoginConstantes.NO_AUTORIZADO.getMsg(),
					null);
		}
		
		return logResp;
	}
	

}
