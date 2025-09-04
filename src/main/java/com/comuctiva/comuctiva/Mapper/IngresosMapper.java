package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.IngresosDto;
import com.comuctiva.comuctiva.models.Ingresos;

public interface IngresosMapper {
Ingresos toIngresos(IngresosDto ingresosDto);
IngresosDto toIngresosDto(Ingresos ingresos);
List<IngresosDto> toIngresosDtoList(List<Ingresos> ingresoss);
void updateIngresos(Ingresos ingresos, IngresosDto ingresosDto);
}
