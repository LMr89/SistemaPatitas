package com.patitas.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patitas.app.controller.response.ResultadoResponse;
import com.patitas.app.model.entity.Mascota;
import com.patitas.app.util.MascotaHttp;


@Controller
@RequestMapping("/Mascota")
public class MascotaController {
	@GetMapping("/frmMantMascota")
	public String frmListarMascota(Model model) throws Exception{
				model.addAttribute("listmascotas", MascotaHttp.listarMascotas());
		return "/Mascota/frmMantMascota";
	}
	
	@PostMapping("/registrarMascota")
	@ResponseBody
	public ResultadoResponse registrarMascota(@RequestBody Mascota objMascota) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = MascotaHttp.registrarMascota(objMascota);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Mascota no resgistrado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@PostMapping("/actualizarMascota")
	@ResponseBody
	public ResultadoResponse actulizarMascota(@RequestBody Mascota objMascota) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = MascotaHttp.actualizarMascota(objMascota);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Mascota no actualizado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@PostMapping("/eliminarMascota")
	@ResponseBody
	public ResultadoResponse eliminarMascota(@RequestBody Mascota objMascota) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = MascotaHttp.eliminarMascota(objMascota);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Mascota no eliminado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@GetMapping("/listarMascotas")
	@ResponseBody
	public List<Mascota> listarMascotas() throws IOException{
		return MascotaHttp.listarMascotas();
	}
	
}
