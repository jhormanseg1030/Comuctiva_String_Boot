package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {
    private Integer idComentario;
    private String comentario;
    private LocalDateTime fechaComentario;
    private Integer idCompProduc;
    private Integer idUsuario;
    private String nombreUsuario;
    private String nombreProducto;
}
