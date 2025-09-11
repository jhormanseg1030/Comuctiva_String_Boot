package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.models.Calilficaciones_produc;

public interface Califi_ProducMapper {
    Calilficaciones_produc toCalilficaciones_produc (Califi_ProduDto califi_ProduDto);
    Califi_ProduDto toCalifi_ProduDto (Calilficaciones_produc calilficaciones_produc);
    List<Califi_ProduDto> toCalifi_ProduDtoList(List<Calilficaciones_produc>califi_pro);
    void updateCalilficaciones_produc(Calilficaciones_produc calilficaciones_produc, Califi_ProduDto califi_ProduDto);
}
