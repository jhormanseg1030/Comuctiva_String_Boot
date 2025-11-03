package com.comuctiva.comuctiva.services;

import com.comuctiva.comuctiva.Dto.VehiculoCreateDto;
import com.comuctiva.comuctiva.Dto.VehiculoDto;
import com.comuctiva.comuctiva.Dto.VehiculoEstadoDto;
import com.comuctiva.comuctiva.Dto.VehiculoUpdateDto;

import java.util.List;

public interface VehiculoServices {
    
    List<VehiculoDto> listarTodos();
    
    VehiculoDto buscarPorId(Integer id);
    
    VehiculoDto crear(VehiculoCreateDto createDto);
    
    VehiculoDto actualizar(VehiculoUpdateDto updateDto);
    
    VehiculoDto cambiarEstado(Integer id, VehiculoEstadoDto estadoDto);
    
    void eliminar(Integer id);
}
