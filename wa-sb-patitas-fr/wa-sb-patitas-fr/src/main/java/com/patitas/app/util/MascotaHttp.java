package com.patitas.app.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.patitas.app.model.entity.Mascota;
import com.patitas.app.model.response.MascotaResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MascotaHttp {
	public static List<Mascota> listarMascotas() throws IOException {
		final String URL = "http://localhost:8070/api/mascota/listar";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(URL).build();
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseBody responseBody = client.newCall(request).execute().body();
		List<Mascota> entity = (List<Mascota>) objectMapper.readValue(responseBody.string(),
				new TypeReference<List<Mascota>>() {
				});
		return entity;
	}
	
	  public static String registrarMascota(Mascota mas) throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("application/json");
		    RequestBody body = RequestBody.create(mediaType, "{\n        "
					+ "\"id\": "+mas.getId() +",\n "
					+ "\"nombre\": "+mas.getIdCliente() +",\n"
					+ "\"apellidos\":"+mas.getNombre() +",\n"
					+ "\"dni\":" +mas.getIdRaza() +",\n"
					+ "\"direccion\":" +mas.getColor() +",\n"
					+ "\"email\":" +mas.getIdEspecie() +",\n"
					+ "\"telefono\":" +mas.getEstado() +",\n"       
					+ "\"estado\":"+mas.getEstado() +"\n}");
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/mascota/crear")
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
	  
	  public static String actualizarMascota(Mascota mas) throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("application/json");
		    RequestBody body = RequestBody.create(mediaType, "{\n        "
					+ "\"id\": "+mas.getId() +",\n "
					+ "\"nombre\": "+mas.getIdCliente() +",\n"
					+ "\"apellidos\":"+mas.getNombre() +",\n"
					+ "\"dni\":" +mas.getIdRaza() +",\n"
					+ "\"direccion\":" +mas.getColor() +",\n"
					+ "\"email\":" +mas.getIdEspecie() +",\n"
					+ "\"telefono\":" +mas.getEstado() +",\n"       
					+ "\"estado\":"+mas.getEstado() +"\n}");
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/mascota/actualizar")
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
	  
	  public static String eliminarMascota(Mascota mas) throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("text/plain");
		    RequestBody body = RequestBody.create(mediaType, "{\n        "
					+ "\"id\": "+mas.getId());
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/mascota/eliminar")
		      .method("DELETE", body)
		      .build();
			Response response = client.newCall(request).execute();
			String codigo =("Codigo de respuesta: " + response.code());							
			if(response.isSuccessful()) {
				codigo = response.body().string();												
			}
		return codigo;
	}
	  
	  public static List<MascotaResponse> buscarMascotasPorIdCliente(Integer id) throws IOException {
			final String URL = "http://localhost:8070/api/mascota/buscar/"+id;
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(URL).build();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			ResponseBody responseBody = client.newCall(request).execute().body();
			List<MascotaResponse> mascotas =  objectMapper.readValue(responseBody.string(),
					new TypeReference<List<MascotaResponse>>() {
					});
			return mascotas;
		}
}
