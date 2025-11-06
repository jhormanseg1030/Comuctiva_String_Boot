package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Com_ProducDto {

    private Integer id_com_produc;
    private Integer id_producto;
    private String nombre_producto;
    private Short cantidad;
    private Double precio;
    private Double subtotal;
}
