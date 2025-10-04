package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produc_CarriDto {

    @NotNull(message = "El campo producto es obligatorio")
    private Integer id_producto;

    @NotNull(message = "El campo carro es obligatorio")
    private Integer id_carro;

    private String nompro;
}
