package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Guia_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.Dto.Guia_EnvioUpdateDto;

public interface Guia_EnvioServices {
    Guia_EnvioDto crearGuia_Envio(Guia_EnvioCrearDtos guia_enviocCrearDtos);

    Guia_EnvioDto guia_envioPorId(Integer id);

    List<Guia_EnvioDto> listartodos();

    void eliminarGuia_Envio(Integer id);

    Guia_EnvioDto actualizarGuia_Envio(Guia_EnvioUpdateDto guia_EnvioUpdateDto);

}
