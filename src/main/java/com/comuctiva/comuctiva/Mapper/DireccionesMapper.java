package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.models.Direcciones;

public interface DireccionesMapper {
    Direcciones toDirecciones(DireccionesDto direccionesDto);

    DireccionesDto toDireccionesDto(Direcciones direcciones);

    List<DireccionesDto> toDireccionesDtoList(List<Direcciones> direccioness);
    
    void updateDirecciones(Direcciones direcciones, DireccionesDto direccionesDto);
}
