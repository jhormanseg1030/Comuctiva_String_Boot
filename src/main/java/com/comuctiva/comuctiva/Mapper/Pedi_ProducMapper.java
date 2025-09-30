package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Pedi_ProducDto;
import com.comuctiva.comuctiva.Dto.PedidosAsignadosDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado2Dto;
import com.comuctiva.comuctiva.models.Pedi_Produc;

public interface Pedi_ProducMapper {
    
    Pedi_Produc toPedi_Produc(Pedi_ProducDto pedi_ProducDto);

    Pedi_ProducDto toPedi_ProducDto(Pedi_Produc pedi_Produc);

    PedidosAsignadosDto toPedi_AsigDto(Pedi_Produc pedi);

    ProductoAsignado2Dto toProd_Asig(Pedi_Produc prod);

}
