package com.patitas.app.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patitas.app.model.entity.Cliente;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ClienteHttp {
	static Cliente sampleResponse = new Cliente();

	public static List<Cliente> listarClientes() throws IOException {
		final String URL = "http://localhost:8070/api/cliente/listar";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(URL).build();
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseBody responseBody = client.newCall(request).execute().body();
		List<Cliente> entity = (List<Cliente>) objectMapper.readValue(responseBody.string(),
				new TypeReference<List<Cliente>>() {
				});
		return entity;
	}

	public static String registrarCliente(Cliente cli) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\n        "
				+ "\"id\": "+cli.getId() +",\n "
				+ "\"nombre\": "+cli.getNombre() +",\n"
				+ "\"apellidos\":"+cli.getApellidos() +",\n"
				+ "\"dni\":" +cli.getDni() +",\n"
				+ "\"direccion\":" +cli.getDireccion() +",\n"
				+ "\"email\":" +cli.getEmail() +",\n"
				+ "\"telefono\":" +cli.getTelefono() +",\n"       
				+ "\"estado\":"+cli.getEstado() +"\n}");
		Request request = new Request.Builder()
			.url("http://localhost:8070/api/cliente/crear")
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
	
	  public static String actualizarCliente(Cliente cli)  throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("application/json");
		    RequestBody body = RequestBody.create(mediaType, "{\n        "
					+ "\"id\": "+cli.getId() +",\n "
					+ "\"nombre\": "+cli.getNombre() +",\n"
					+ "\"apellidos\":"+cli.getApellidos() +",\n"
					+ "\"dni\":" +cli.getDni() +",\n"
					+ "\"direccion\":" +cli.getDireccion() +",\n"
					+ "\"email\":" +cli.getEmail() +",\n"
					+ "\"telefono\":" +cli.getTelefono() +",\n"       
					+ "\"estado\":"+cli.getEstado() +"\n}");
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/cliente/actualizar")
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
	
	  public static String eliminarCliente(Cliente cli) throws IOException{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		      .build();
		    MediaType mediaType = MediaType.parse("text/plain");
		    RequestBody body = RequestBody.create(mediaType, "{\n        "
					+ "\"id\": "+cli.getId());
		    Request request = new Request.Builder()
		      .url("http://localhost:8070/api/cliente/eliminar")
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
