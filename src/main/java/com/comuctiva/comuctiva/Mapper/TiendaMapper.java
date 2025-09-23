package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.TiendaCreateDto;
import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.models.Tienda;

public interface TiendaMapper {
    Tienda toTienda(TiendaCreateDto tiendaCreateDto);

    TiendaDto toTiendaDto(Tienda tienda);
}
