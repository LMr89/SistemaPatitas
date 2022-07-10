package com.patitas.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("/usuario")
	public String frmListarUsuario(Model model) {
		return "/Usuario/frmMantUsuario";
	}
	
	

	
}
