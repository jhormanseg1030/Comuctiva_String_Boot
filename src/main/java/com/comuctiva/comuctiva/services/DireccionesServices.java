package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.DireccionesDto;

public interface DireccionesServices {

    DireccionesDto crearDireccion(DireccionesDto direccionesDto);

    DireccionesDto direccionPorId(Integer id);

    List<DireccionesDto> listarTodas();

    void eliminarDireccion(Integer id);
}
