package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Guia_De_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_De_EnvioDto;
import com.comuctiva.comuctiva.Dto.Guia_De_EnvioUpdateDto;

public interface Guia_De_EnvioServices {
    Guia_De_EnvioDto crearGuia_Envio(Guia_De_EnvioCrearDtos guia_enviocCrearDtos);

    Guia_De_EnvioDto guia_envioPorId(Integer id);

    List<Guia_De_EnvioDto> listartodos();

    void eliminarGuia_Envio(Integer id);

    Guia_De_EnvioDto actualizarGuia_Envio(Guia_De_EnvioUpdateDto guia_EnvioUpdateDto);

}
