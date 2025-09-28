package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Dto.Compra_AsignacionesDto;
import com.comuctiva.comuctiva.Dto.Producto_AsignacionesDto;

public interface Comp_ProducServices {
    Com_ProducDto asignar(Com_ProducDto com_p);

    List<Com_ProducDto> listarCompra(Integer compraId);

    List<Compra_AsignacionesDto> listarCompra2(Integer compraId);

    List<Com_ProducDto> listarProducto(Integer productoId);

    List<Producto_AsignacionesDto> listarProducto2(Integer productoId);

    void eliminar(Integer compraId, Integer productoId);
}
