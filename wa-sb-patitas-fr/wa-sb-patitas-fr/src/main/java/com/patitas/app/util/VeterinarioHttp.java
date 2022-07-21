package com.patitas.app.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patitas.app.model.entity.Veterinario;
import com.patitas.app.model.request.VeterinarioRequest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class VeterinarioHttp {

	public static List<Veterinario> listarVeterinarios() throws IOException {
		final String URL = "http://localhost:8070/api/veterinario/listar";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(URL).build();
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseBody responseBody = client.newCall(request).execute().body();
		List<Veterinario> entity = (List<Veterinario>) objectMapper.readValue(responseBody.string(),
				new TypeReference<List<Veterinario>>() {
				});
		return entity;
	}
	public static Integer obtenerIdVeterinarioPorIdCliente(Integer idUsuario) throws IOException {
		Integer id = 0;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/veterinario/buscar/"+idUsuario)
				  .build();
				Response response = client.newCall(request).execute();
				if (response.isSuccessful()) {
					id = Integer.parseInt(response.body().string());
					
				}
				return id;
	}
	
	

	public static String registrarVeterinario(VeterinarioRequest vet) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\n"
				+ "\"id\": "+vet.getId() +",\n "
				+ "\"idUSuario\": "+vet.getIdUsuario() +",\n"+
				"\n}");
		
		Request request = new Request.Builder()
			.url("http://localhost:8070/api/veterinario/crear")
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
	
	  public static String actualizarVeterinario(VeterinarioRequest vet)  throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("application/json");
		    RequestBody body = RequestBody.create(mediaType, "{\n        "
					+ "\"id\": "+vet.getId() +",\n "
					+ "\"idUSuario\": "+vet.getIdUsuario() +",\n"
					 +"\n}");
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/veterinario/actualizar")
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
	
	  public static String eliminarVet(Integer id) throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("text/plain");
		    RequestBody body = RequestBody.create(mediaType, "");
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/veterinario/eliminar/"+ id)
		      .method("DELETE", body)
		      .build();
			Response response = client.newCall(request).execute();
			String codigo =("Codigo de respuesta: " + response.code());							
			if(response.isSuccessful()) {
				codigo = response.body().string();												
			}
		return codigo;
	 }
}
