package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.models.Comp_Produc;

public interface VentaMapper {
    
    VentaDto toVentaDto(Comp_Produc comp_produc);
}
