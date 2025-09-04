package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.models.Guia_Envio;

public interface Guia_de_EnvioMapper {
Guia_Envio toGuia_Envio(Guia_EnvioDto guia_envioDto);
Guia_EnvioDto toGuia_EnvioDto(Guia_Envio guia_envio);
List<Guia_EnvioDto> toGuia_EnvioDtoList(List<Guia_Envio> guia_envios);
void updateGuia_Envio( Guia_Envio guia_envio, Guia_EnvioDto guia_envioDto );
}
