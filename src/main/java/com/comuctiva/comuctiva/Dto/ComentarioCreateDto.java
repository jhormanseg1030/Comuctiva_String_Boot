package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioCreateDto {
    @NotNull(message = "El ID de compra-producto es requerido")
    private Integer idCompProduc;

    @NotNull(message = "El ID de usuario es requerido")
    private Integer idUsuario;

    @NotBlank(message = "El comentario no puede estar vacío")
    private String comentario;
}
