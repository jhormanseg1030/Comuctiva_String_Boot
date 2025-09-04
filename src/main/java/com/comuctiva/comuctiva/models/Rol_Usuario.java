package com.comuctiva.comuctiva.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
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
    @EmbeddedId
    private Rol_UsuarioId id = new Rol_UsuarioId();

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_rol_usuario"))
    private Usuario usuari;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "FK_usuario_rol"))
    private Rol rol;
}
