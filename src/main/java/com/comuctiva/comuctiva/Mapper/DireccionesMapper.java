package com.comuctiva.comuctiva.Mapper;


import com.comuctiva.comuctiva.Dto.DireccionesCreateDto;
import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.models.Direcciones;

public interface DireccionesMapper {
    Direcciones toDirecciones(DireccionesCreateDto direccionesCreateDto);

    DireccionesDto toDireccionesDto(Direcciones direcciones);
}
