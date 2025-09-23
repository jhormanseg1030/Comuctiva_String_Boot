package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Califi_ProduCreateDto;
import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.Dto.CalificacionUpdateDto;

public interface Califi_ProducServices {

    Califi_ProduDto crearCalif_Produ(Califi_ProduCreateDto califi_ProduCreateDto);

    Califi_ProduDto califi_ProduPorId(Integer id);

    List<Califi_ProduDto> listar();

    void eliminarCalif_Produ(Integer id);

    Califi_ProduDto actualizarCalif_Produ(CalificacionUpdateDto califiUpdate);
}
