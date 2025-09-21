package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.models.Compra;

public interface CompraMapper {
    Compra toCompra(CompraCreateDto compraCreateDto);

    CompraDto toCompraDto(Compra compra);
}
