package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.models.Barrio;

public interface BarrioMapper {
    Barrio toBarrio(BarrioDto barrioDto);

    BarrioDto toBarrioDto(Barrio barrio);

    List<BarrioDto> toBarrioDtoList(List<Barrio>barrios);
    
    void updateBarrio(Barrio barrio, BarrioDto barrioDto);
}
