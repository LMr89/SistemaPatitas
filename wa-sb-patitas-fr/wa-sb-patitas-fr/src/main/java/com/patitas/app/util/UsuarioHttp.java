package com.patitas.app.util;

import java.io.IOException;

import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patitas.app.model.entity.Usuario;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;




public class UsuarioHttp {
	static Usuario sampleResponse = new Usuario();
	public static List<Usuario> listarUsuarios() throws IOException{
		final String URL = "http://localhost:8070/api/usuario/listar";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(URL)
				.build();
		ObjectMapper objectMapper = new ObjectMapper(); 
		ResponseBody responseBody = client.newCall(request).execute().body(); 
		List<Usuario>entity =  (List<Usuario>) objectMapper.readValue(responseBody.string(), new TypeReference<List<Usuario>>() {
		});
		return entity;
	}
	
	public static String registrarUsuario(Usuario us) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\n        "
						+ "\"id\": "+us.getId() +",\n "
						+ "\"nombre\": "+us.getNombre() +",\n"
						+ "\"apellidos\":"+us.getApellidos() +",\n"
						+ "\"dni\":" +us.getDni() +",\n"
						+ "\"email\":" +us.getEmail() +",\n"
						+ "\"telefono\":" +us.getTelefono() +",\n"
						+ "\"estado\":" +us.getEstado() +",\n"       
						+ "\"direccion\":"+us.getDireccion() +"\n}");
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/usuario/crear")
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
	
	public static String actualizarUsuario(Usuario us) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\n        "
						+ "\"id\": "+us.getId() +",\n "
						+ "\"nombre\": "+us.getNombre() +",\n"
						+ "\"apellidos\":"+us.getApellidos() +",\n"
						+ "\"dni\":" +us.getDni() +",\n"
						+ "\"email\":" +us.getEmail() +",\n"
						+ "\"telefono\":" +us.getTelefono() +",\n"
						+ "\"estado\":" +us.getEstado() +",\n"       
						+ "\"direccion\":"+us.getDireccion() +"\n}");
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/usuario/actualizar")
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
	
	
	
	
	public static String eliminarUsuario(Usuario us) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "{\n        "
						+ "\"id\": "+us.getId());
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/usuario/eliminar")
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
