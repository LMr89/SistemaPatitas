package com.patitas.api.app.infraestructura.local.adaptadores.archMaestros;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitas.api.app.aplicacion.intefaces.archMaestros.IUsuario;
import com.patitas.api.app.dominio.entidades.archMaestro.Usuario;
import com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro.UsuarioJpa;
import com.patitas.api.app.infraestructura.local.repositorio.repositorios.archMaestro.UsuarioRep;
import com.patitas.api.app.infraestructura.mapeadores.archMaestros.UsuarioMapper;

@Service
public class UsuarioAdaptador  implements IUsuario{
	
	@Autowired
	private UsuarioRep usuarioRep;

	@Override
	public List<Usuario> listarUsuarios() {
		
		return usuarioRep.findAll()
				.stream()
				.map(UsuarioMapper::mapDeEntidadJpaADominio)
				.collect(Collectors.toList())
				;
	}

	@Override
	public Usuario crearNuevoUsuario(Usuario cli) {
		UsuarioJpa usuarioCreado = usuarioRep.save(UsuarioMapper.mapDeDominioAEntidadJpa(cli));
		return  UsuarioMapper.mapDeEntidadJpaADominio(usuarioCreado);
	}

	@Override
	public Optional<Usuario> encontrarUsuarioPorId(Integer id) {
		return Optional.of(UsuarioMapper.mapDeEntidadJpaADominio(
				usuarioRep.findById(id).get()));
	}

	@Override
	public Usuario actualizarUsuario(Usuario cli) {
		UsuarioJpa usuarioActualizado = usuarioRep.save(UsuarioMapper.mapDeDominioAEntidadJpa(cli));
		return  UsuarioMapper.mapDeEntidadJpaADominio(usuarioActualizado);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		usuarioRep.delete(UsuarioMapper.mapDeDominioAEntidadJpa(
				encontrarUsuarioPorId(id).get()));
		
	}

	
}
