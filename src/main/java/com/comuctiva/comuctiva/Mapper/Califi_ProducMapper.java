package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Califi_ProduCreateDto;
import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.models.Calilficaciones_produc;

public interface Califi_ProducMapper {
    Calilficaciones_produc toCalilficaciones_produc (Califi_ProduCreateDto califi_ProduCreateDto);
    
    Califi_ProduDto toCalifi_ProduDto (Calilficaciones_produc calilficaciones_produc);
}
