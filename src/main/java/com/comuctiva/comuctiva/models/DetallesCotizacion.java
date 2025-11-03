package com.comuctiva.comuctiva.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetallesCotizacion {
    private Double base;
    private Double distancia;
    private Double seguro;
    private Double peajes;
    private Double urgencia;
    private Double factorCarga;
    private Double subtotal;
    private Double iva;
}
