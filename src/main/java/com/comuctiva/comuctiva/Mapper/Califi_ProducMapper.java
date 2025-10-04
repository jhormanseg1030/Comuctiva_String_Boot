package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Califi_ProduCreateDto;
import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.models.Calificaciones_produc;

public interface Califi_ProducMapper {
    Calificaciones_produc toCalilficaciones_produc (Califi_ProduCreateDto califi_ProduCreateDto);
    
    Califi_ProduDto toCalifi_ProduDto (Calificaciones_produc calilficaciones_produc);
}
