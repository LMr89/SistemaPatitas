package com.patitas.api.app.aplicacion.intefaces.autenticacion;

import com.patitas.api.app.dominio.modelo.peticion.autenticacion.LoginPeticion;
import com.patitas.api.app.dominio.modelo.respuesta.autenticacion.LoginRespuesta;

public interface ILogin {
	LoginRespuesta iniciarSesion(LoginPeticion log);
	

}
