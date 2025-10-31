package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.Guia_De_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_De_EnvioDto;
import com.comuctiva.comuctiva.models.Guia_De_Envio;

    public interface Guia_de_EnvioMapper {
    Guia_De_Envio toGuia_Envio(Guia_De_EnvioCrearDtos guia_envioCrearDtos);

    Guia_De_EnvioDto toGuia_EnvioDto(Guia_De_Envio guia_envio);
}
