package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.BarrioCreateDto;
import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.Dto.BarrioUpdateDto;

public interface BarrioServices {

    BarrioDto crearBarrioDto(BarrioCreateDto barrioCreateDto);

    BarrioDto barrioPorId(Integer id);

    List<BarrioDto> listartodos();

    void eliminarPedidos(Integer id);

    BarrioDto actualizarBarrio(BarrioUpdateDto barrioUpdateDto);
}
