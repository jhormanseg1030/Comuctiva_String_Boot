package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CarritoCreateDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoUpdateDto;

public interface CarritoServices {

    CarritoDto crearCarrito(CarritoCreateDto carritoCreateDto);

    CarritoDto carritoPorId(Integer id);

    List<CarritoDto> listartodos();

    void eliminarCarrito(Integer id);

    CarritoDto actualizarCarrito(CarritoUpdateDto carritoUpdateDto);
}