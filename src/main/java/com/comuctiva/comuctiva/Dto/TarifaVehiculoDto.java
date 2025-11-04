package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarifaVehiculoDto {
    private String nombre;
    private String icono;
    private Double tarifaBase;
    private Double costoKm;
    private Integer maxDistancia;
    private Integer capacidadKg;
    private String descripcion;
}
