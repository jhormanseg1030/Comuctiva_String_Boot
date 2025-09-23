package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.TiendaCreateDto;
import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.Dto.TiendaUpdateDto;

public interface TiendaServices {

    TiendaDto crearTienda(TiendaCreateDto tiendaCreateDto);

    TiendaDto tiendaPorId(Integer id);

    List<TiendaDto> listartodos();

    void eliminarTienda(Integer id);

    TiendaDto actualizarTienda(TiendaUpdateDto tiendaUpdateDto);
}
