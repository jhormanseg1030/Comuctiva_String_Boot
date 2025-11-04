package com.comuctiva.comuctiva.services;


import com.comuctiva.comuctiva.Dto.AgregarCarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;

public interface CarritoServices {
    CarritoDto obtenerCarrito(Integer idUsuario);
    
    CarritoDto agregarProducto(Integer idUsuario, AgregarCarritoDto dto);
    
    CarritoDto actualizarCantidad(Integer idUsuario, Integer idProducto, Integer cantidad);
    
    CarritoDto eliminarProducto(Integer idUsuario, Integer idProducto);
    
    void vaciarCarrito(Integer idUsuario);
}