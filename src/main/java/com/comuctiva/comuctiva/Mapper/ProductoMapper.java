package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.models.Producto;

public interface ProductoMapper {
    Producto toProducto(ProductoCreateDto productoCreateDto);

    ProductoDto toProductoDto(Producto producto);
}
