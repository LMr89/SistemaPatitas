package com.patitas.app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.patitas.app.model.request.LoginRequest;
import com.patitas.app.model.response.LoginResponse;
import com.patitas.app.util.LoginHttp;

@Controller
public class LoginController {
	@GetMapping({"/","/login"})
	public String login(Model model) {
		model.addAttribute("loginform", new LoginRequest());
		model.addAttribute("visualizar", false);
		return "patitas/login/index";
	}
	
	@PostMapping("/login")
	public String autenticar(@ModelAttribute("loginform") LoginRequest objLogin,
			Model model) {
		LoginResponse respuesta = null;
		try {
			
			respuesta = LoginHttp.iniciarsesion(objLogin);
			if(respuesta.getEsAutenticado() != false) {
				model.addAttribute("mensaje",respuesta.getMensaje());
				
				return "patitas/home";
			}
			else {
				model.addAttribute("visualizar", true);
				model.addAttribute("mensaje",respuesta.getMensaje());
				return"patitas/login/index";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("visualizar", true);
		model.addAttribute("mensaje",respuesta.getMensaje());
		return"patitas/login/index";
	}

}
