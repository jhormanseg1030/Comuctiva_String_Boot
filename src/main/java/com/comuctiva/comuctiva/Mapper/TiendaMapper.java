package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.models.Tienda;

public interface TiendaMapper {
Tienda toTienda(TiendaDto tiendaDto);
TiendaDto toTiendaDto(Tienda tienda);
List<TiendaDto> toTiendaDtoList(List<Tienda>tiendas);
void updateTienda(Tienda tienda, TiendaDto tiendaDto);
}
