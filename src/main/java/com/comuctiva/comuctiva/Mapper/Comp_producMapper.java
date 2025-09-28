package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Dto.Compra_AsignacionesDto;
import com.comuctiva.comuctiva.Dto.Producto_AsignacionesDto;
import com.comuctiva.comuctiva.models.Comp_Produc;

public interface Comp_producMapper {

    Comp_Produc toComp_Produc(Com_ProducDto comp_producDto);

    Com_ProducDto toCom_ProducDto(Comp_Produc comp_produc);

    Compra_AsignacionesDto toCompra_AsignacionesDto(Comp_Produc comp);

    Producto_AsignacionesDto toProducto_AsignacionesDto(Comp_Produc prod);
}
