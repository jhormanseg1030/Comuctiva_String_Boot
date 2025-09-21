package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.DireccionesCreateDto;
import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.Dto.DireccionesUpdateDto;

public interface DireccionesServices {

    DireccionesDto crearDireccion(DireccionesCreateDto direccionesCreateDto);

    DireccionesDto direccionPorId(Integer id);

    List<DireccionesDto> listarTodas();

    void eliminarDireccion(Integer id);

    DireccionesDto actualizarDireccion(DireccionesUpdateDto direccionesUpdateDto);
}
