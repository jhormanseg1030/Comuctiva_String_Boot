package com.comuctiva.comuctiva.Mapper;


import com.comuctiva.comuctiva.Dto.CarritoCreateDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.models.Carrito;

public interface CarritoMapper {
    Carrito toCarrito(CarritoCreateDto carritoCreateDto);

    CarritoDto toCarritoDto(Carrito carrito);
}
