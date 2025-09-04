package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.models.Carrito;

public interface CarritoMapper {
Carrito toCarrito(CarritoDto carritoDto);
CarritoDto toCarritoDto(Carrito carrito);
List<CarritoDto> toCarritoDtoList(List<Carrito>carritos);
void updateCarrito(Carrito carrito, CarritoDto carritoDto);
}
