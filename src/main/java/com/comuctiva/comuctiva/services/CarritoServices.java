package com.comuctiva.comuctiva.services;


import com.comuctiva.comuctiva.Dto.AgregarCarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;

public interface CarritoServices {
    CarritoDto obtenerCarrito(Integer id_Usuario);
    
    CarritoDto agregarProducto(Integer id_Usuario, AgregarCarritoDto dto);
    
    CarritoDto actualizarCantidad(Integer id_Usuario, Integer idProducto, Integer cantidad);
    
    CarritoDto eliminarProducto(Integer id_Usuario, Integer idProducto);
    
    void vaciarCarrito(Integer id_Usuario);
}