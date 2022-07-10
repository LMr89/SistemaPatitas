package com.patitas.app.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.patitas.app.model.LoginForm;


@Controller
public class LoginController {
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("loginform", new LoginForm());
		model.addAttribute("visualizar", false);
		return "login";
	}
	
	@PostMapping("/login")
	public String autenticar(@ModelAttribute("loginform") LoginForm objLogin,
			Model model) {
		if(objLogin.getUsuario().equals("andres") &&
			objLogin.getPassword().equals("123456")){
			//model.addAttribute("mensaje", "Bienvenido: "+ objLogin.getUsuario());
			return "layout";
		}
		model.addAttribute("visualizar", true);
		return"login";
	}

}
