package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingres_ProducDto {

    @NotNull(message = "El ID de la compra no puede ser nulo")
    private Integer id_ingresos;

    @NotNull(message = "El ID de la compra no puede ser nulo")
    private Integer id_Producto;
    private String nombre_producto;

    private Short cantidad;
}
