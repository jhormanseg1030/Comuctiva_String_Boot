package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReglasGeneralesDto {
    private Double seguroPct;
    private Double ivaPct;
    private Double peajeEstimadoPorKm;
    private Double recargoUrgenciaPct;
    private Double factorCargaPesada;
}
