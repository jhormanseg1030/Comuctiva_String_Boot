package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedi_ProducDto {

    @NotNull( message = "El ID del pedido no puede ser nulo")
    private Integer id_pedido;

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Integer id_producto;
    private String nombre_del_producto;
    
    private Short cantidad;
    private Double valor;

}
