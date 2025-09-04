package com.comuctiva.comuctiva.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rol_UsuarioId implements Serializable {
    private Long usuarioId;
    private Long rolId;
}
