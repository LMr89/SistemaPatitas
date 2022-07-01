package com.patitas.api.app.infraestructura.mapeadores.archMaestros;

import com.patitas.api.app.dominio.entidades.Cliente;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.ClienteJpa;

public class ClienteMaper {
	public static  Cliente mapDeEntidadJpaADominio(ClienteJpa cliEnti) {
		return new Cliente(
				cliEnti.getIdcliente(),
				cliEnti.getNombre(),
				cliEnti.getApellidos(),
				cliEnti.getDni(),
				cliEnti.getDireccion(),
				cliEnti.getCorreo(),
				cliEnti.getTelefono(),
				cliEnti.getEstado());
	}
	
	public  static ClienteJpa mapDeDominioAEntidadJpa(Cliente clieDom) {
		return new ClienteJpa(
				clieDom.getId(),
				clieDom.getNombre(),
				clieDom.getApellidos(),
				clieDom.getDni(),
				clieDom.getDireccion(),
				clieDom.getEmail(),
				clieDom.getTelefono(),
				clieDom.getEstado());
	}

}
