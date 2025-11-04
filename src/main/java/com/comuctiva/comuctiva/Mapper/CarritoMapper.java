package com.comuctiva.comuctiva.Mapper;


import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.models.Produc_Carri;

public interface CarritoMapper {
    CarritoDto toCarritoDto(Carrito carrito);

    Produc_CarriDto toProduc_CarriDto(Produc_Carri producCarri);
}
