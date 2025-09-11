package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CarritoDto;

public interface CarritoServices {

    CarritoDto crearCarrito(CarritoDto carritoDto);

    CarritoDto carritoPorId(Integer id);

    List<CarritoDto> listartodos();

    void eliminarCarrito(Integer id);
}