package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MiCompraDto {
    private Integer id_compra;
    private Integer id_producto;
    private String producto_nombre;
    private String vendedor_nombre;
    private Integer id_vendedor;
    private Short cantidad;
    private Double valor;
    private Double total;
    private LocalDateTime fecha;
}
