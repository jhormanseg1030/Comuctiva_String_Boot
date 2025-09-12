package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.BarrioDto;

public interface BarrioServices {

    BarrioDto crearBarrioDto(BarrioDto barrioDto);

    BarrioDto barrioPorId(Integer id);

    List<BarrioDto> listartodos();

    void updateBarrio(Integer id, BarrioDto barrioDto);
}
