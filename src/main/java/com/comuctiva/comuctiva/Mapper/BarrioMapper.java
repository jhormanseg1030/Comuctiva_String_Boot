package com.comuctiva.comuctiva.Mapper;


import com.comuctiva.comuctiva.Dto.BarrioCreateDto;
import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.models.Barrio;

public interface BarrioMapper {
    Barrio toBarrio(BarrioCreateDto barrioCreateDto);

    BarrioDto toBarrioDto(Barrio barrio);
}
