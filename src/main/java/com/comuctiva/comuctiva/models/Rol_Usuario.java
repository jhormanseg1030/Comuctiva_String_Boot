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

    @EmbeddedId
    private Rol_UsuarioId id = new Rol_UsuarioId();
    
    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "ID_Usuario", foreignKey = @ForeignKey(name = "FK_usuario_roles"))
    private Usuario usuario;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "ID_Rol", foreignKey = @ForeignKey(name = "FK_rol_usuarios_xd"))
    private Rol rol;

    private Boolean estado;
}
