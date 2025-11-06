package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Com_ProducCreateDto;
import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.models.Comp_Produc;

public interface Comp_producMapper {

    Comp_Produc toCompProducto(Com_ProducCreateDto compraProductoCreateDto);

    Com_ProducDto toCompraProductoDto(Comp_Produc comp_produc);
}
