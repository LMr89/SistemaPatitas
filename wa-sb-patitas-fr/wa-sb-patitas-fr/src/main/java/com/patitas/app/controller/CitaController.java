package com.patitas.app.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.patitas.app.controller.response.ResultadoResponse;
import com.patitas.app.model.entity.Cita;
import com.patitas.app.model.entity.Cliente;
import com.patitas.app.model.response.MascotaResponse;
import com.patitas.app.util.CitaHttp;
import com.patitas.app.util.MascotaHttp;


@Controller
@RequestMapping("/Cita")
public class CitaController {
	@GetMapping("/index")
	public String frmListarCita(Model model) throws Exception{
		model.addAttribute("ventaproductoform", new Cita());
		model.addAttribute("listcitas", CitaHttp.listarCitas());
		model.addAttribute("visualizar", false);
	return "patitas/Cita/frmMantCita";
	}
	
	@PostMapping("/registrarCita")
	@ResponseBody
	public ResultadoResponse registrarCita(@RequestBody Cita objCita) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = CitaHttp.registrarCita(objCita);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Cita no resgistrado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@PutMapping("/actualizarCita")
	@ResponseBody
	public ResultadoResponse actualizarCita(@RequestBody Cita objCita) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = CitaHttp.actualizarCita(objCita);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Cita no actualizada";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@DeleteMapping("/eliminarCita/{id}")
	@ResponseBody
	public ResultadoResponse eliminarCita(@PathVariable("id") String id) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = CitaHttp.eliminarCita(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Cita no eliminada";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@GetMapping("/listarCitas") 
	@ResponseBody
	public List<Cita> listarCitas() throws IOException{
		return CitaHttp.listarCitas();
	}

	@GetMapping("/buscarCliente")
	@ResponseBody
	public Cliente buscar(@RequestParam("dni")  String dni) throws IOException{
		return CitaHttp.buscarClientePorDni(dni);
	}
	
	@GetMapping("/obtener-mascotas")
	@ResponseBody
	public List<MascotaResponse> obtenerMascotas(@RequestParam("id")  Integer id) throws IOException{
		return MascotaHttp.buscarMascotasPorIdCliente(id);
	}
}
