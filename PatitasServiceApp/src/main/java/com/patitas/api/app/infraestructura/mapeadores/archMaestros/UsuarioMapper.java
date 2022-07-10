package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;

public class UsuarioMapper {
	public static  Usuario mapDeEntidadJpaADominio(UsuarioJpa cliEnti) {
		return new Usuario(
				cliEnti.getId(),
				cliEnti.getNombre(),
				cliEnti.getApellidos(),
				cliEnti.getDni(),
				cliEnti.getDireccion(),
				cliEnti.getCorreo(),
				cliEnti.getTelefono(),
				cliEnti.getEstado());
	}
	
	public  static UsuarioJpa mapDeDominioAEntidadJpa(Usuario usuarioDom) {
		return new UsuarioJpa(
				usuarioDom.getId(),
				usuarioDom.getNombre(),
				usuarioDom.getApellidos(),
				usuarioDom.getDni(),
				usuarioDom.getDireccion(),
				usuarioDom.getEmail(),
				usuarioDom.getTelefono(),
				usuarioDom.getEstado());
	}
}
