package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.IngresosDto;
import com.comuctiva.comuctiva.models.Ingresos;

public interface IngresosMapper {
Ingresos toIngresos(Ingresos ingresos);
IngresosDto toIngresosDto(IngresosDto ingresosDto);
List<IngresosDto> toIngresosDtoList(List<Ingresos> ingresoss);
void updateIngresos(Ingresos ingresos, IngresosDto ingresosDto);
}
