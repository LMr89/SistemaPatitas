package com.patitas.app.util;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patitas.app.model.request.LoginRequest;
import com.patitas.app.model.response.LoginResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginHttp {

	public static LoginResponse iniciarsesion(LoginRequest log) throws IOException {		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\r\n    \"nomUsuario\":\""+log.getNomUsuario()+"\",\r\n    \"contrasenia\":\""+log.getContrasenia()+"\"\r\n}");
				ObjectMapper objectMapper = new ObjectMapper();
				Request request = new Request.Builder()
				  .url("http://localhost:8070/api/auth")
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response response = client.newCall(request).execute();	
				//Map<String,Object> values =  objectMapper.readValue(response.body().string(),
					//	new TypeReference<HashMap<String, Object>>() {
				//		});
				LoginResponse entity =  objectMapper.readValue(response.body().string(),
						LoginResponse.class);				
			System.out.println(entity.toString());
			//System.out.println(response.body().string());
			//System.out.println(values.get("accesos"));
				
		
		
		return entity;

	}

}
