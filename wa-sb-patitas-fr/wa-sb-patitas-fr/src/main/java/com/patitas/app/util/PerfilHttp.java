package com.patitas.app.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patitas.app.model.entity.PerfilUsuario;
import com.patitas.app.model.entity.Usuario;
import com.patitas.app.model.request.PerfilRequest;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class PerfilHttp {
    public static PerfilUsuario obtenerPerfil(Integer id) throws IOException {
        final String URL = "http://localhost:8070/api/perfil/buscar/" +id;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = client.newCall(request).execute().body();
        PerfilUsuario entity =  objectMapper.readValue(responseBody.string(),
                PerfilUsuario.class);
        return entity;
    }

    public static String crearPerfil(PerfilRequest perReq) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n   " +
                " \"nombre\": \""+perReq.getNombreUsuario()+"\",\n  " +
                "  \"contraseña\": \""+perReq.getContrasenia()+"\",\n  " +
                "  \"idAcceso\": "+perReq.getIdAcceso()+",\n  " +
                "  \"idUsuario\":"+perReq.getIdUsuario()+"\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8070/api/perfil/crear")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String resp = "Error al crear";
        if(response.isSuccessful()) {
            resp = response.body().string();
        }
        return resp;
    }

    public static String actualizarPerfil(PerfilRequest perReq)throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n   " +
                " \"idPerfil\": \""+perReq.getIdPerfil()+"\",\n    " +
                " \"nombre\": \""+perReq.getNombreUsuario()+"\",\n    " +
                "\"contraseña\": \""+perReq.getContrasenia()+"\",\n  " +
                " \"idAcceso\":"+perReq.getIdAcceso()+ " ,\n    " +
                "\"idUsuario\":"+perReq.getIdUsuario()+ "\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8070/api/perfil/actualizar")
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String resp = "Error al actualizar";
        if(response.isSuccessful()) {
            resp = response.body().string();
        }
        return resp;
    }

}
