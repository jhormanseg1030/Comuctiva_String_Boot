package com.comuctiva.comuctiva.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rol_Usuario {
private Boolean estado;
    @Embeddable
    public class Rol_UsuarioId implements Serializable{
        private Long usuarioId;
        private Long rolId;
    }
    @EmbeddedId
    private Rol_UsuarioId id = new Rol_UsuarioId();

    @ManyToOne
    @MapsId("usuarioId")
    private Usuario usuari;

    @ManyToOne
    @MapsId("rolId")
    private Rol rol;
}
