package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CompraDto;

public interface CompraServices {
    CompraDto crearCompra(CompraDto compraDto);

    CompraDto compraPorId(Integer id);

    List<CompraDto> listartodos();
    
    void eliminarCompra(Integer id);
}
