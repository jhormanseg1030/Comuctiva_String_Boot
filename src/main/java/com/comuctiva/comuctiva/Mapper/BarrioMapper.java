package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.models.Barrio;

public interface BarrioMapper {
Barrio toBarrio(BarrioDto barrioDto);
BarrioDto ToBarrioDto(Barrio barrio);
List<BarrioDto> toBarrioDtoList(List<Barrio>barrios);
Void updateBarrio(Barrio barrio, BarrioDto barrioDto);
}
