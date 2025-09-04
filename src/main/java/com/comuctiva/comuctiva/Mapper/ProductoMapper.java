package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.models.Producto;

public interface ProductoMapper {
Producto toProducto(ProductoDto productoDto);
ProductoDto toProductoDto(Producto producto);
List<ProductoDto> toProductoDtoList(List<Producto>produc);
void updateProducto(Producto producto, ProductoDto productoDto);
}
