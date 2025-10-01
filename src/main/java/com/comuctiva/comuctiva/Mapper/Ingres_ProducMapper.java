package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Ingres_ProducDto;
import com.comuctiva.comuctiva.Dto.IngresosAsigDto;
import com.comuctiva.comuctiva.Dto.ProducAsig4Dto;
import com.comuctiva.comuctiva.models.Ingres_Produc;

public interface Ingres_ProducMapper {

    Ingres_Produc toIngres_ProducDto(Ingres_ProducDto ingresDto);

    Ingres_ProducDto toIngresProDto(Ingres_Produc ingres_Produc);

    IngresosAsigDto toIngresAsig(Ingres_Produc asigIngre);

    ProducAsig4Dto toproAsig(Ingres_Produc asigPro);
}
