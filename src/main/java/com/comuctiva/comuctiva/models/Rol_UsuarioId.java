package com.comuctiva.comuctiva.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of ={"usuarioId","rolId"})
public class Rol_UsuarioId implements Serializable {
    public Rol_UsuarioId(Integer id_Usuario, Integer id_rol){
    }
    private Long usuarioId;
    private Long rolId;
}
