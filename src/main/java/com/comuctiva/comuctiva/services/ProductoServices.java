package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.Dto.ProductoUpdateDto;

public interface ProductoServices {
    ProductoDto crearProducto(ProductoCreateDto productoCreateDto);
    ProductoDto crearProductoConUsuario(ProductoCreateDto productoCreateDto, String documento);
    ProductoDto productoPorId(Integer id);
    List<ProductoDto> listar();
    List<ProductoDto> listarPorDocumentoVendedor(String documento);
    void eliminarProducto(Integer id);
    ProductoDto actualizarProducto(ProductoUpdateDto productoUpdateDto);
}
