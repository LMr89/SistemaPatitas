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
import com.patitas.app.model.entity.Cliente;
import com.patitas.app.util.ClienteHttp;


@Controller
@RequestMapping("/Cliente")
public class ClienteController {

	@GetMapping("/frmMantCliente")
	public String frmListarCliente(Model model) throws Exception{
				model.addAttribute("listclientes", ClienteHttp.listarClientes());
		return "/Cliente/frmMantCliente";
	}
	
	@PostMapping("/registrarCliente")
	@ResponseBody
	public ResultadoResponse registrarCliente(@RequestBody Cliente objCliente) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = ClienteHttp.registrarCliente(objCliente);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Cliente no resgistrado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	@PostMapping("/actulizarCliente")
	@ResponseBody
	public ResultadoResponse actulizarCliente(@RequestBody Cliente objCliente) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = ClienteHttp.actualizarCliente(objCliente);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Cliente no actualizado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@PostMapping("/eliminarCliente")
	@ResponseBody
	public ResultadoResponse eliminarCliente(@RequestBody Cliente objCliente) {
		String mensaje=" " ;
		Boolean respuesta = true;
		try {
			mensaje = ClienteHttp.eliminarCliente(objCliente);
		}
		catch(Exception e) {
			e.printStackTrace();
			mensaje = "Cliente no eliminado";
			respuesta= false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@GetMapping("/listarClientes")
	@ResponseBody
	public List<Cliente> listarClientes() throws IOException{
		return ClienteHttp.listarClientes();
	}
}
