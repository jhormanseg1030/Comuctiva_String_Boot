package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;

public interface Guia_EnvioServices {
    Guia_EnvioDto crearGuia_Envio(Guia_EnvioDto guia_envioDto);

    Guia_EnvioDto guia_envioPorId(Integer id);

    List<Guia_EnvioDto> listartodos();

    void eliminarGuia_Envio(Integer id);

}
