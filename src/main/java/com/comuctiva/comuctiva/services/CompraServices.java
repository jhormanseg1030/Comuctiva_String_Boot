package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.Dto.CompraUpdateDto;

public interface CompraServices {
    CompraDto crearCompra(CompraCreateDto compraCreateDto);

    CompraDto compraPorId(Integer id);

    List<CompraDto> listartodos();
    
    void eliminarCompra(Integer id);

    CompraDto actualizarCompra(CompraUpdateDto compraUpdateDto);
}
