package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.CarritoAsignacionDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado3Dto;
import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.models.Produc_Carri;

public interface Produc_CarriMapper {

    Produc_Carri toProduc_Carri(Produc_CarriDto producto_CarritoDto);

    Produc_CarriDto toProduc_CarriDto(Produc_Carri produc_Carri);

    ProductoAsignado3Dto toProducAsig3(Produc_Carri produc);

    CarritoAsignacionDto toCarritoAsig(Produc_Carri carri);
}
