package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.VehiculoCreateDto;
import com.comuctiva.comuctiva.Dto.VehiculoDto;
import com.comuctiva.comuctiva.Dto.VehiculoUpdateDto;
import com.comuctiva.comuctiva.models.Vehiculo;

public interface VehiculoMapper {
    
    VehiculoDto toDto(Vehiculo vehiculo);
    
    Vehiculo toEntity(VehiculoCreateDto createDto);
    
    void updateEntityFromDto(VehiculoUpdateDto updateDto, Vehiculo vehiculo);
}
