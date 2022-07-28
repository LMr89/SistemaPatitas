package com.patitas.app.controller;

import com.patitas.app.controller.response.ResultadoResponse;
import com.patitas.app.model.entity.PerfilUsuario;
import com.patitas.app.model.request.PerfilRequest;
import com.patitas.app.util.PerfilHttp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/PerfilUsuario")
public class PerfilController {
	
    @GetMapping("/obtenerPerfil")
    @ResponseBody
    public PerfilUsuario obtenerPerfil(@RequestParam("idUsuario") Integer id) throws IOException {
        return PerfilHttp.obtenerPerfil(id);
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public ResultadoResponse actualizarPerfil(@RequestBody PerfilRequest prfReq) {
        String mensaje = "";
        Boolean respuesta = true;
        try {
            mensaje =  PerfilHttp.actualizarPerfil(prfReq);

        } catch (IOException e) {
            mensaje = "perfil no actualizado";
            respuesta= false;
        }
        return new ResultadoResponse(respuesta, mensaje);

    }

    @PostMapping("/crear")
    @ResponseBody
    public ResultadoResponse crearPerfil(@RequestBody PerfilRequest prfReq) {
        String mensaje = "";
        Boolean respuesta = true;
        try {
            mensaje =  PerfilHttp.crearPerfil(prfReq);

        } catch (IOException e) {
            mensaje = "No se creo un perfil";
            respuesta= false;
        }
        return new ResultadoResponse(respuesta, mensaje);

    }

}
