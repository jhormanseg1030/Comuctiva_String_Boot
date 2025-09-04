package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.ReembolsosDto;
import com.comuctiva.comuctiva.models.Reembolsos;

public interface ReembolsosMapper {
Reembolsos toReembolsos(ReembolsosDto ReembolsosDto);
ReembolsosDto toReembolsosDto(Reembolsos reembolsos);
List<ReembolsosDto>toReembolsosDtoList(List<Reembolsos>reembolsos);
void updateReembolsos(Reembolsos reembolsos, ReembolsosDto reembolsosDto);
}
