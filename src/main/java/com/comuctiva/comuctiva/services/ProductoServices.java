package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.Dto.ProductoUpdateDto;

public interface ProductoServices {

    ProductoDto crearProducto(ProductoCreateDto productoCreateDto);

    ProductoDto productoPorId(Integer id);

    List<ProductoDto> listar();
    // Método eliminado: listarPorDocumentoVendedor - No hay relación Producto-Usuario

    void eliminarProducto(Integer id);

    ProductoDto actualizarProducto(ProductoUpdateDto productoUpdateDto);

    List<ProductoDto> listarMisProductos(Integer idUsuario);

    void desactivarProducto(Integer id);
    
    void restaurarProducto(Integer id);
}
