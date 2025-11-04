package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDto {
    private Integer id_pro;
    private String nombre_Producto;
    private Double valor;
    private Short cantidad;
    private String imagen;
    private String descripcion;
    private String categoria;
    private Integer id_medida;
    private Integer id_usuario;
}
