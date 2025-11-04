package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetallesCotizacionDto {
    private Double base;
    private Double distancia;
    private Double seguro;
    private Double peajes;
    private Double urgencia;
    private Double factorCarga;
    private Double subtotal;
    private Double iva;
}
