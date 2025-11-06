package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaDto {
    private Integer id_compra;
    private LocalDateTime fec_com;
    private Double total;
    private String nombreUsuario;
    private String nomprod;
    private Short cantidad;
    private Double precio;
}
