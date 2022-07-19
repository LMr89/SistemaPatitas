package com.patitas.api.app.infraestructura.local.repositorio.entidades.archMaestro;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdministrador")
    private Integer idAdministrador;

    @JsonBackReference(value = "administrador_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioJpa idUsuario;
    
    @Column(name="estado")
    private Boolean estado;
}
