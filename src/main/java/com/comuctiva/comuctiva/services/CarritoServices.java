package com.comuctiva.comuctiva.services;


import com.comuctiva.comuctiva.Dto.AgregarCarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;

public interface CarritoServices {
    CarritoDto obtenerCarrito(Integer id_usuario);
    
    CarritoDto agregarProducto(Integer id_usuario, AgregarCarritoDto dto);
    
    CarritoDto actualizarCantidad(Integer id_usuario, Integer idProducto, Integer cantidad);
    
    CarritoDto eliminarProducto(Integer id_usuario, Integer idProducto);
    
    void vaciarCarrito(Integer id_usuario);
}