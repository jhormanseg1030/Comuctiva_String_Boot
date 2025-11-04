package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfigFletesDto {
    private Map<String, TarifaVehiculoDto> vehiculos;
    private ReglasGeneralesDto reglas;
}
