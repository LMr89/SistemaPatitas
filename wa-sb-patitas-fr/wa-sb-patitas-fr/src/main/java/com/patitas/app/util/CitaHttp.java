package com.patitas.app.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patitas.app.model.entity.Cita;
import com.patitas.app.model.entity.Cliente;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CitaHttp {
	public static List<Cita> listarCitas() throws IOException{
		final String URL = "http://localhost:8070/api/cita/listar";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(URL).build();
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseBody responseBody = client.newCall(request).execute().body();
		List<Cita> entity = (List<Cita>) objectMapper.readValue(responseBody.string(),
				new TypeReference<List<Cita>>() {
				});
		return entity;
	}
	
	public static String registrarCita(Cita cit) throws IOException{
		
	    OkHttpClient client = new OkHttpClient().newBuilder()
	    	      .build();
	    	    MediaType mediaType = MediaType.parse("application/json");
	    	    RequestBody body = RequestBody.create(mediaType, "{\r\n    "
	    	    		+ "\"idCita\":\""+cit.getId()+"\",\r\n    "
	    	    		+ "\"idCliente\":\""+cit.getCliente().get("id")+"\",\r\n"
	    	    		+ "\"idMascota\":\""+cit.getMascota().get("idMascota")+"\",\r\n"
	    	    		+ "\"idVeterinario\":\""+cit.getVeterinario().get("id")+"\",\r\n"
	    	    		+ "\"idRecepcionista\":\""+cit.getRecepcionista().get("idRecepcionista")+"\",\r\n"
	    	    		+ "\"fechaRegistro\":\""+CitaHttp.parsearCalendarAString(cit.getFechaRegistro())+"\",\r\n"
	    	    		+ "\"fechaAtencion\":\""+CitaHttp.parsearCalendarAString(cit.getFechaAtencion())+"\",\r\n"
	    	    		+ "\"pendiente\":\""+cit.getPendiente()+"\" \r\n}");

	    	    
	    	    System.out.println(cit.getCliente().get("id") + " " +cit.getMascota().get("idMascota") );
	    	    Request request = new Request.Builder()
	    	      .url("http://localhost:8070/api/cita/crear")
	    	      .method("POST", body)
	    	      .addHeader("Content-Type", "application/json")
	    	      .build();
				Response response = client.newCall(request).execute();
				String codigo =("Codigo de respuesta: " + response.code());							
				if(response.isSuccessful()) {
					codigo = response.body().string();												
				}
			return codigo;	
	}
	
	public static String actualizarCita(Cita cit) throws IOException{
	    OkHttpClient client = new OkHttpClient().newBuilder()
	    	      .build();
	    	    MediaType mediaType = MediaType.parse("application/json");
	    	    RequestBody body = RequestBody.create(mediaType, "{\r\n    "
	    	    		+ "\"idCita\":\""+cit.getId()+"\",\r\n    "
	    	    		+ "\"idCliente\":\""+cit.getCliente().get("id")+"\",\r\n"
	    	    		+ "\"idMascota\":\""+cit.getMascota().get("idMascota")+"\",\r\n"
	    	    		+ "\"idVeterinario\":\""+cit.getVeterinario().get("id")+"\",\r\n"
	    	    		+ "\"idRecepcionista\":\""+cit.getRecepcionista().get("idRecepcionista")+"\",\r\n"
	    	    		+ "\"fechaRegistro\":\""+CitaHttp.parsearCalendarAString(cit.getFechaRegistro())+"\",\r\n"
	    	    		+ "\"fechaAtencion\":\""+CitaHttp.parsearCalendarAString(cit.getFechaAtencion())+"\",\r\n"
	    	    		+ "\"pendiente\":\""+cit.getPendiente()+"\"\r\n}");
	    	    Request request = new Request.Builder()
	    	      .url("http://localhost:8070/api/cita/actualizar")
	    	      .method("PUT", body)
	    	      .addHeader("Content-Type", "application/json")
	    	      .build();
				Response response = client.newCall(request).execute();
				String codigo =("Codigo de respuesta: " + response.code());							
				if(response.isSuccessful()) {
					codigo = response.body().string();												
				}
			return codigo;	
	}
	
	public static String eliminarCita(String id) throws IOException{
		final String URL = "http://localhost:8070/api/cita/eliminar/" + id;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url(URL)
				  .method("DELETE", body)
				  .build();
				Response response = client.newCall(request).execute();

				String codigo = "";							
				if(response.isSuccessful()) {
					codigo = response.body().string();												
				}
				

			return codigo;
	}

	public static Cliente buscarClientePorDni(String dni) throws IOException {
		
		final String URL = "http://localhost:8070/api/cliente/buscar-dni/" + dni;
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(URL).build();
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseBody responseBody = client.newCall(request).execute().body();
		Cliente clienteEncontrado = objectMapper.readValue(responseBody.string(),
				Cliente.class);
		
				
		return clienteEncontrado;
	}
	public static String parsearCalendarAString(Calendar cal) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		Date d = cal.getTime();
		String formato = sdf.format(d);
	
		return formato ;
	}
}



