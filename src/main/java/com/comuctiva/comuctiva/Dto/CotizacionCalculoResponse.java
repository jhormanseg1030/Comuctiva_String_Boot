package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CotizacionCalculoResponse {
    private DetallesCotizacionDto detalles;
    private Double total;
    private String mensaje;
}
