package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.models.Guia_Envio;

@Component
public class Guia_EnvioMapperImple implements Guia_de_EnvioMapper{

@Override
public Guia_Envio toGuia_Envio(Guia_EnvioDto guia_envioDto){
if (guia_envioDto== null) {
    return null;
}
Guia_Envio guia_envio= new Guia_Envio();
guia_envio.setId_guia(guia_envioDto.getId_gui());
guia_envio.setFec_env(guia_envioDto.getFech_en());
return guia_envio;
}
@Override
public Guia_EnvioDto toGuia_EnvioDto (Guia_Envio guia_envio){
    if (guia_envio== null) {
        return null;
    }
    Guia_EnvioDto guia_envioDto = new Guia_EnvioDto();
    guia_envioDto.setId_gui(guia_envio.getId_guia());
    guia_envioDto.setFech_en(guia_envio.getFec_env());
    return guia_envioDto;
}
@Override
public List<Guia_EnvioDto>toGuia_EnvioDtoList(List<Guia_Envio> guia_envios){
    if (guia_envios== null) {
        return List.of();
    }
    List<Guia_EnvioDto> guia_envioDtos = new ArrayList<Guia_EnvioDto>(guia_envios.size());
    for(Guia_Envio guia_Envio: guia_envios){
        guia_envioDtos.add(toGuia_EnvioDto(guia_Envio));
    }
    return guia_envioDtos;
}
@Override
public void updateGuia_Envio(Guia_Envio guia_envio,Guia_EnvioDto guia_envioDto){
    if (guia_envioDto == null) {
        return;
    }
    guia_envio.setId_guia(guia_envioDto.getId_gui());
    guia_envio.setFec_env(guia_envioDto.getFech_en());
}
}
