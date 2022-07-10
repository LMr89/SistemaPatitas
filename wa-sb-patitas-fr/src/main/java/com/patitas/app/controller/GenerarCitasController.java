package com.patitas.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenerarCitasController {
	
	@GetMapping("/cita")
	public String generarCita(Model model) {
		return "/Cita/frmMantCita";
	}
}
