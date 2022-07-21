package com.patitas.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuario {
    private Integer id;
    private String nombreUsuario;
    private String contraseniaUsuario;
    private Accesos idAcceso;
    private Usuario idUSuario;

}
