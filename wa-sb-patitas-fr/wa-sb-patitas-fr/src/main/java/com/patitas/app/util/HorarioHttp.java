package com.patitas.app.util;

import java.io.IOException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.patitas.app.model.request.HorarioRequest;

import okhttp3.*;

public class HorarioHttp {
	private static Logger log = LogManager.getLogger(HorarioHttp.class);
	
	public static String registrarHorario(HorarioRequest req) throws IOException, ParseException {
		
		String respuesta = "";
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\r\n   "
						+ " \"idVeterinario\":"+ req.getIdVet()+",\r\n  "
						+ "  \"manInicio\":\""+req.convertirHoraADate(req.getManInicio())+"\",\r\n  "
						+ "  \"manFin\":\""+req.convertirHoraADate(req.getManFin())+"\",\r\n  "
						+ "  \"tarInicio\":\""+req.convertirHoraADate(req.getTarInicio())+"\",\r\n  "
						+ "  \"tarFin\":\""+req.convertirHoraADate(req.getTarFin())+"\"\r\n}");
				
				//log.info(req.convertirHoraADate(req.getHoraIncio()));
				
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/horario/registrar")
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response response = client.newCall(request).execute();
				if(response.isSuccessful()) {
					respuesta = response.body().string();												
				}
		
		
		return respuesta;
	}
}
