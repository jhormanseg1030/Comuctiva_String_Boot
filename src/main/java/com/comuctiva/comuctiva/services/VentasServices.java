package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.VentaDto;

public interface VentasServices {
    
    List<VentaDto> obtenerMisVentas(Integer id_usuario);
    
    List<VentaDto> listarTodas();
}
