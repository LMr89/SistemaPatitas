package com.patitas.app.controller;


import com.patitas.app.controller.response.ResultadoResponse;
import com.patitas.app.model.request.HorarioRequest;
import com.patitas.app.util.HorarioHttp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/horario")
public class HorarioController {
    //Logger
    private static Logger log = LogManager.getLogger(HorarioController.class);

    @GetMapping("/existe-horario")
    @ResponseBody
    public ResultadoResponse hayHorario(@RequestParam("idUsu") Integer idUsuario){
        String msg = "";
        Boolean estado = false;
         try{
            msg =  HorarioHttp.obtenerHorarioPorIdUsuario(idUsuario);

            if (!msg.isEmpty()){
                estado = true;
            }
            log.info(msg);

         }catch (Exception e){
            log.error("Ocurrio un error: "  + e.getCause());
            estado = false;
            msg = e.getMessage();
         }
         return  new ResultadoResponse(estado, msg);

    }
    @PostMapping(path = "/registrar-horario" )
    @ResponseBody
    public String registrarHorario(@RequestBody HorarioRequest rq) {

        log.info("Datos obtenidos del front: " + rq.toString());

        String resultado = "";
        try {
            resultado = HorarioHttp.registrarHorario(rq);

        } catch (Exception e) {
            resultado = "Al registrar el horario Usuario Controller FRONT";
        }

        return resultado;
    }
    @PutMapping(path = "/actualizar-horario" )
    @ResponseBody
    public String actualizarHorario(@RequestBody HorarioRequest rq) {

        log.info("Datos obtenidos del front para actualizar: " + rq.toString());

        String resultado = "";
        try {
            resultado = HorarioHttp.actualizarHorario(rq);

        } catch (Exception e) {
            resultado = "Al actualizar el horario Usuario Controller FRONT";
        }

        return resultado;
    }

}
