package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Guia_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.models.Guia_Envio;

    public interface Guia_de_EnvioMapper {
    Guia_Envio toGuia_Envio(Guia_EnvioCrearDtos guia_envioCrearDtos);

    Guia_EnvioDto toGuia_EnvioDto(Guia_Envio guia_envio);
}
