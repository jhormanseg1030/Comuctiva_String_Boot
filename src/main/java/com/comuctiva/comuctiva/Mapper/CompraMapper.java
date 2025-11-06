package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Compra;

public interface CompraMapper {
    Compra toCompra(CompraCreateDto compraCreateDto);

    CompraDto toCompraDto(Compra compra);

    Com_ProducDto toCompraProductoDto(Comp_Produc comp_produc);
}
