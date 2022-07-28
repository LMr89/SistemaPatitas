package com.patitas.app.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.patitas.app.controller.response.ResultadoResponse;
import com.patitas.app.model.entity.Usuario;
import com.patitas.app.model.request.HorarioRequest;
import com.patitas.app.util.HorarioHttp;
import com.patitas.app.util.UsuarioHttp;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {
	private static Logger log = LogManager.getLogger(UsuarioController.class);
		
	@GetMapping("/index")
	public String frmListarUsuario(Model model) throws Exception{
				model.addAttribute("listusuarios", UsuarioHttp.listarUsuarios());
		return "patitas/Usuario/frmMantUsuario";
	}	

	@PostMapping("/registrarUsuario")
	@ResponseBody
	public ResultadoResponse registrarUsuario(@RequestBody Usuario objUsuario) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = UsuarioHttp.registrarUsuario(objUsuario);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Usuario no resgistrado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@PutMapping("/actualizarUsuario")
	@ResponseBody
	public ResultadoResponse actualizarUsuario(@RequestBody Usuario objUsuario) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = UsuarioHttp.actualizarUsuario(objUsuario);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Usuario no actualizado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@PostMapping("/eliminarUsuario")
	@ResponseBody
	public ResultadoResponse eliminarUsuario(@RequestBody Usuario objUsuario) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = UsuarioHttp.eliminarUsuario(String.valueOf(objUsuario.getId()));
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Usuario no eliminado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	
	@GetMapping("/listarUsuarios")
	@ResponseBody
	public List<Usuario> listarUsuarios() throws IOException{
		return UsuarioHttp.listarUsuarios();
	}
	



}
