package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.Dto.ProductoUpdateDto;

public interface ProductoServices {

    ProductoDto crearProducto(ProductoCreateDto productoCreateDto);

    ProductoDto productoPorId(Integer id);

    List<ProductoDto> listar();

    List<ProductoDto> listarPendientes();

    void cambiarEstadoProducto(Integer id, String nuevoEstado);

    void eliminarProducto(Integer id);

    ProductoDto actualizarProducto(ProductoUpdateDto productoUpdateDto);

    List<ProductoDto> listarMisProductos(Integer id_usuario);

    void desactivarProducto(Integer id);
    
    void restaurarProducto(Integer id);

        List<ProductoDto> obtenerProductosPorVendedor(Integer id_usuario);
    
    ProductoDto obtenerProductoPorIdYVendedor(Integer id_producto, Integer id_usuario);
    
    ProductoDto actualizarProductoVendedor(Integer id_producto, Integer id_usuario, ProductoUpdateDto dto);
    
    boolean desactivarProductoVendedor(Integer id_producto, Integer id_usuario);
    
    boolean activarProductoVendedor(Integer id_producto, Integer id_usuario);
}
