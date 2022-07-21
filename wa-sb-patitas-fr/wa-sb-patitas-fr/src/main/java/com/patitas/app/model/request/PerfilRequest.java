package com.patitas.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilRequest {
    private Integer idPerfil;
    private String nombreUsuario;
    private String contrasenia;
    private Integer idAcceso;
    private Integer idUsuario;
}
