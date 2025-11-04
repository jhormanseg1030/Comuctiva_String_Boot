package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produc_CarriDto {
    private Integer idProducto;
    private Integer id_carro;
    private String nombreProducto;
    private Double valor;
    private Integer cantidad;
    private String imagen;
    private String categoria;
    private Double subtotal;
}
