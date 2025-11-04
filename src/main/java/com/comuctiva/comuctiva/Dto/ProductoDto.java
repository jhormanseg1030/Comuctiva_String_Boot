package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDto {
    private Integer id_pro;
    private String nombre_Producto;      // ✅ Cambiado de "nopro"
    private Double valor;                // ✅ Cambiado de "valoor"
    private Short cantidad;              // ✅ Cambiado de "cantid"
    private String imagen;               // ✅ Cambiado de "image"
    private String descripcion;          // ✅ Cambiado de "descri"
    private String categoria;            // ✅ OK
    private Integer id_medida;           // ✅ Cambiado de "id_medi"
    private String estado;               // Estado del producto (pendiente, aprobado, rechazado)
    private Boolean activo;              // Estado activo/inactivo del producto
}